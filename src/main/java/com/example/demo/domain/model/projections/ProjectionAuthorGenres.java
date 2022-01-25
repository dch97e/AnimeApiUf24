package com.example.demo.domain.model.projections;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;
import java.util.UUID;
@JsonPropertyOrder({"authorid", "name", "image"})
public interface ProjectionAuthorGenres {
    UUID getAuthorid();
    String getName();
    String getImage();

    @JsonIgnoreProperties("authors")
    Set<ProjectionAnimeGenres> getAnimes();
}
