package com.example.demo.domain.model.projections;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;
import java.util.UUID;

@JsonPropertyOrder({"animeid" , "name","description","type","year" ,"image"})
public interface ProjectionAnimeGenres {
    UUID getAnimeid();
    String getName();
    String getType();
    int getYear();
    String getImage();
    String getDescription();


    @JsonIgnoreProperties("animes")
    Set<ProjectionAuthor> getAuthors();
    @JsonIgnoreProperties("animes")
    Set<ProjectionGenre> getGenres();
}
