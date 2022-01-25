package com.example.demo.domain.model;


import com.example.demo.domain.model.projections.ProjectionAnime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID authorid;
    public String name;
    public String image;

    @ManyToMany(mappedBy = "authors")
    @JsonIgnoreProperties("authors")
    public Set<Anime> animes;


}