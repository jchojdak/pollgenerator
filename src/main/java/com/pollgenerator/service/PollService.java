package com.pollgenerator.service;

import com.pollgenerator.model.Poll;
import com.pollgenerator.model.PollVote;
import com.pollgenerator.model.dto.PollResultDto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface PollService {
    List<Poll> getAll();

    Poll save(Poll poll);

    Poll getById(UUID pollId);

    List<PollVote> addVotes(UUID pollId, Set<UUID> optionIds, String ipAddress);

    List<PollResultDto> getPollResultsById(UUID pollId);
}
