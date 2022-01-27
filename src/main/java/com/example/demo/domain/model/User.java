package com.example.demo.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="usser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID userid;

    public String username;
    public String password;
    public String role;
    public boolean enabled;

    @ManyToMany(mappedBy = "favoritedBy")
    @JsonIgnoreProperties("favoritedBy")
    public Set<Anime> favorites;

    @ManyToMany(mappedBy = "blockBy")
    @JsonIgnoreProperties("blockBy")
    public Set<Anime> blocks;

    @JoinTable(name = "follow_user", joinColumns = {
            @JoinColumn(name = "followuser", referencedColumnName = "userid", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "follower", referencedColumnName = "userid", nullable = false)})
    @ManyToMany
    private Set<User> follow;


    @ManyToMany(mappedBy = "follow")
    private Set<User> followBy;


}
