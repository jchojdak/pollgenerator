package com.pollgenerator.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "poll_options")
public class PollOption {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "value")
    private String value;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "poll_id")
    @Schema(hidden = true)
    private Poll poll;



}
