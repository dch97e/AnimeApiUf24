package com.example.demo.domain.model;

import com.example.demo.domain.model.compositekeys.UserAnimeKey;
import com.example.demo.domain.model.compositekeys.UserFollowKey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "follow_user")
@IdClass(UserFollowKey.class)
public class UserFollow {
    @Id
    public UUID followuser;
    @Id
    public UUID follower;

}
