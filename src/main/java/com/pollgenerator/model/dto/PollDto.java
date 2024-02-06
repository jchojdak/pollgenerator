package com.pollgenerator.model.dto;

import com.pollgenerator.model.PollType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class PollDto {

    private String title;
    private String description;
    private PollType type;
    private List<PollOptionDto> options;

}
