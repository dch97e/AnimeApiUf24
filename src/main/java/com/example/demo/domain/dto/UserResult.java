package com.example.demo.domain.dto;

import java.util.UUID;

public class UserResult {
    public UUID userid;
    public String username;

    public UserResult(UUID userid, String username) {
        this.userid = userid;
        this.username = username;
    }
}
