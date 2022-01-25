
package com.example.demo.domain.model.projections;

import com.example.demo.domain.model.Genre;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;
import java.util.UUID;
@JsonPropertyOrder({"animeid" , "name", "image"})
public interface ProjectionAnime {
    UUID getAnimeid();
    String getName();
    String getImage();


    @JsonIgnoreProperties("animes")
    Set<ProjectionAuthor> getAuthors();


}