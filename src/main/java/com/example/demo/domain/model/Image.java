package com.example.demo.domain.model;

import com.example.demo.domain.model.compositekeys.ImageAnimeKey;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="images")
@IdClass(ImageAnimeKey.class)
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID imageid;

    @Id
    public UUID animeid;

    public String imageurl;

    @ManyToOne
    @JoinColumn(name = "animeid",insertable = false,nullable = false,updatable = false)
    @JsonIgnoreProperties ("images")
    public Anime animeWithImages;
}
