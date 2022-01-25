package com.example.demo.domain.model;

import com.example.demo.domain.model.compositekeys.UserAnimeKey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="block_anime")
@IdClass(UserAnimeKey.class)
public class BlockAnime {
    @Id
    public UUID userid;
    @Id
    public UUID animeid;
}
