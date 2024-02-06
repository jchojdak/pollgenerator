package com.pollgenerator.service.impl;

import com.pollgenerator.exception.ResourceNotFoundException;
import com.pollgenerator.model.Poll;
import com.pollgenerator.repository.PollRepository;
import com.pollgenerator.service.PollService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PollServiceImpl implements PollService {

    private final PollRepository pollRepository;

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
}
