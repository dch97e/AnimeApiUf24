package com.example.demo.domain.model.projections;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

public interface ProjectionFavorites {
    @JsonIgnoreProperties("favoritedby")
    Set<ProjectionAnimeFavorite> getFavorites();
}