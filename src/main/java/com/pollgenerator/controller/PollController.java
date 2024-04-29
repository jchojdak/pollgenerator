package com.pollgenerator.controller;

import com.pollgenerator.model.Poll;
import com.pollgenerator.model.PollVote;
import com.pollgenerator.model.dto.PollDto;
import com.pollgenerator.model.dto.PollResultDto;
import com.pollgenerator.service.PollService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/polls")
@RequiredArgsConstructor
@Tag(name = "Polls", description = "Endpoints to managing polls")
public class PollController {

    private final PollService pollService;

    private final ModelMapper modelMapper;

    @GetMapping
    @Operation(summary = "Get all polls")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of polls")
    public List<Poll> getAllPolls() {
        return pollService.getAll();
    }

    @GetMapping("/{pollId}")
    @Operation(summary = "Get poll by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved poll"),
            @ApiResponse(responseCode = "404", description = "Poll not found", content = @Content)
    })
    public Poll getPoll(@PathVariable UUID pollId) {
        return pollService.getById(pollId);
    }

    @GetMapping("/{pollId}/results")
    @Operation(summary = "Get poll results by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved poll results"),
            @ApiResponse(responseCode = "404", description = "Poll not found", content = @Content)
    })
    public List<PollResultDto> getPollResults(@PathVariable UUID pollId) {
        return pollService.getPollResultsById(pollId);
    }

    @PostMapping
    @Operation(summary = "Add a new poll")
    @ApiResponse(responseCode = "200", description = "Successfully added new poll")
    public Poll addPoll(@Valid @RequestBody PollDto pollDto) {
        Poll poll = modelMapper.map(pollDto, Poll.class);
        return pollService.save(poll);
    }

    @PostMapping("/{pollId}/vote")
    @Operation(summary = "Vote in a poll")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully saved votes"),
            @ApiResponse(responseCode = "400", description = "Multiple votes in single choice poll", content = @Content),
            @ApiResponse(responseCode = "404", description = "Poll or option not found", content = @Content),
            @ApiResponse(responseCode = "409", description = "Already voted", content = @Content)
    })
    public List<PollVote> addVotes(HttpServletRequest request,
                                  @Valid @NotEmpty @RequestBody Set<UUID> pollOptionIds,
                                  @PathVariable UUID pollId) {
        String ipAddress = request.getRemoteAddr();

        return pollService.addVotes(pollId, pollOptionIds, ipAddress);
    }

}
