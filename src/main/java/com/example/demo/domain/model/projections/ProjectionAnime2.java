package com.example.demo.domain.model.projections;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;
import java.util.UUID;

@JsonPropertyOrder({"animeid" , "name","type" ,"image"})
public interface ProjectionAnime2 {
    UUID getAnimeid();

    String getName();

    String getType();

    String getImage();


    @JsonIgnoreProperties("animes")
    Set<ProjectionAuthor> getAuthors();
}
