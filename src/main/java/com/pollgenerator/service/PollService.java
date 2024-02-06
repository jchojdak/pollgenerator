package com.pollgenerator.service;

import com.pollgenerator.model.Poll;

import java.util.List;
import java.util.UUID;

public interface PollService {
    List<Poll> getAll();

    Poll save(Poll poll);

    Poll getById(UUID pollId);
}
