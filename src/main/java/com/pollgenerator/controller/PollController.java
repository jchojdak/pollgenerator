package com.pollgenerator.controller;

import com.pollgenerator.model.Poll;
import com.pollgenerator.model.PollVote;
import com.pollgenerator.model.dto.PollDto;
import com.pollgenerator.service.PollService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/polls")
@RequiredArgsConstructor
public class PollController {

    private final PollService pollService;

    private final ModelMapper modelMapper;

    @GetMapping
    public List<Poll> getAllPolls() {
        return pollService.getAll();
    }

    @GetMapping("/{pollId}")
    public Poll getPoll(@PathVariable UUID pollId) {
        return pollService.getById(pollId);
    }

    @PostMapping
    public Poll addPoll(@RequestBody PollDto pollDto) {
        Poll poll = modelMapper.map(pollDto, Poll.class);
        return pollService.save(poll);
    }

    @PostMapping("/{pollId}/vote")
    public List<PollVote> addVotes(HttpServletRequest request,
                                  @RequestBody Set<UUID> pollOptionIds,
                                  @PathVariable UUID pollId) {
        String ipAddress = request.getRemoteAddr();

        return pollService.addVotes(pollId, pollOptionIds, ipAddress);
    }

}
