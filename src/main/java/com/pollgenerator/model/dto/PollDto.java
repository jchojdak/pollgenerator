package com.pollgenerator.model.dto;

import com.pollgenerator.model.PollType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PollDto {

    @NotEmpty
    private String title;

    private String description;

    @NotNull
    private PollType type;

    @NotEmpty
    @Size(min = 2)
    private List<PollOptionDto> options;

}
