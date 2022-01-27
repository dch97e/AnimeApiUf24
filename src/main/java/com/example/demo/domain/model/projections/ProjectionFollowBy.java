package com.example.demo.domain.model.projections;

import java.util.Set;

public interface ProjectionFollowBy {
    Set<ProjectionUser> getFollowBy();
}