package com.example.demo.domain.model.projections;

import com.example.demo.domain.model.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;
import java.util.UUID;

@JsonPropertyOrder({"animeid" , "name","type" ,"image"})
public interface ProjectionAnimeImages {
    UUID getAnimeid();

    String getName();

    String getType();

    String getImage();
    @JsonIgnoreProperties("animeWithImages")
    Set<Image> getImages();
}
