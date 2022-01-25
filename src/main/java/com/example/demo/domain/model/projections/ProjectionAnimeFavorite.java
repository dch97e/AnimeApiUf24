package com.example.demo.domain.model.projections;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;


public interface ProjectionAnimeFavorite {

    UUID getAnimeid();
    String getName();
    String getImage();
}