package com.pollgenerator.service.impl;

import com.pollgenerator.exception.AlreadyVotedException;
import com.pollgenerator.exception.MultipleVotesException;
import com.pollgenerator.exception.OptionNotBelongsToPollException;
import com.pollgenerator.exception.ResourceNotFoundException;
import com.pollgenerator.model.Poll;
import com.pollgenerator.model.PollOption;
import com.pollgenerator.model.PollType;
import com.pollgenerator.model.PollVote;
import com.pollgenerator.model.dto.PollResultDto;
import com.pollgenerator.repository.PollOptionRepository;
import com.pollgenerator.repository.PollRepository;
import com.pollgenerator.repository.PollVoteRepository;
import com.pollgenerator.service.PollService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PollServiceImpl implements PollService {

    private final PollRepository pollRepository;
    private final PollOptionRepository pollOptionRepository;
    private final PollVoteRepository pollVoteRepository;

    @Override
    public List<Poll> getAll() {
        return pollRepository.findAll();
    }

    @Override
    public Poll save(Poll poll) {
        poll.setCreatedAt(LocalDateTime.now());
        return pollRepository.save(poll);
    }

    @Override
    public Poll getById(UUID pollId) {
        return pollRepository.findById(pollId)
                .orElseThrow(() -> new ResourceNotFoundException("Poll not found with id: " + pollId));
    }

    @Override
    public List<PollVote> addVotes(UUID pollId, Set<UUID> optionIds, String ipAddress) {
        List<PollVote> votes = new ArrayList<>();

        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new ResourceNotFoundException("Poll not found with id: " + pollId));

        if (poll.getType() == PollType.SINGLE_CHOICE) {
            if (optionIds.stream().count() > 1) throw new MultipleVotesException("Multiple votes are not allowed in this poll");

            List<PollVote> pollVotes = pollVoteRepository.findByIpAddressAndPollOption_Poll_Id(ipAddress, pollId);
            if (!pollVotes.isEmpty()) throw new AlreadyVotedException("You have already voted for this poll (SINGLE_CHOICE)");
        }

        for (UUID optionId : optionIds) {
           PollOption pollOption = pollOptionRepository.findById(optionId)
                    .orElseThrow(() -> new ResourceNotFoundException("Option not found with id: " + optionId));

            if (!pollOption.getPoll().getId().equals(pollId)) {
                throw new OptionNotBelongsToPollException("Option with id " + optionId + " does not belong to poll with id " + pollId);
            }

            List<PollVote> existingVotes = pollVoteRepository.findByIpAddressAndPollOption_Id(ipAddress, optionId);
            if (!existingVotes.isEmpty()) {
                throw new AlreadyVotedException("You have already voted for this option in this poll (MULTIPLE_CHOICE)");
            }

            PollVote vote = new PollVote();
            vote.setPollOption(pollOption);
            vote.setIpAddress(ipAddress);
            votes.add(vote);
        }

        return pollVoteRepository.saveAll(votes);
    }

    @Override
    public List<PollResultDto> getPollResultsById(UUID pollId) {
        List<PollOption> options = pollOptionRepository.findByPollId(pollId);

        return options.stream()
                .map(option -> new PollResultDto(
                        option.getId(),
                        option.getValue(),
                        pollVoteRepository.countByPollOptionId(option.getId())))
                .collect(Collectors.toList());
    }
}
