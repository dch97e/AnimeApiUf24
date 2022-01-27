package com.example.demo.domain.model.projections;

import com.example.demo.domain.model.BlockAnime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;
import java.util.UUID;
@JsonPropertyOrder({"animeid","name"})
public interface ProjectionAnimeBlock {
    UUID getAnimeid();
    String getName();

}
