package com.example.demo.domain.model.projections;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;
import java.util.UUID;

@JsonPropertyOrder({"userid", "username", "blocks"})
public interface ProjectionUserAnimes {
    UUID getUserid();
    String getUsername();

    @JsonIgnoreProperties("blockBy")
    Set<ProjectionAnimeBlock> getBlocks();



}
