package com.example.demo.domain.model.projections;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;
import java.util.UUID;

@JsonPropertyOrder({"authorid" , "name"})
public interface ProjectionAuthor {
    UUID getAuthorid();
    String getName();


    @JsonIgnoreProperties("authors")
    Set<ProjectionAnime> getAnimes();
}