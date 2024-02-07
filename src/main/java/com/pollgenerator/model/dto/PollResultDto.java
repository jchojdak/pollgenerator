package com.pollgenerator.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class PollResultDto {

    private UUID id;
    private String value;
    private Long count;

}
