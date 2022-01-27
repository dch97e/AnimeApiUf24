package com.example.demo.domain.model.compositekeys;

import java.io.Serializable;
import java.util.UUID;

public class UserFollowKey implements Serializable {
    public UUID followuser;
    public UUID follower;
}
