
package com.example.demo.domain.model.projections;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;
import java.util.UUID;

@JsonPropertyOrder({"genreid", "label"})
public interface ProjectionGenre2 {
    UUID getGenreid();
    String getLabel();


    @JsonIgnoreProperties("genres")
    Set<ProjectionAnimeGenres> getAnimes();
}