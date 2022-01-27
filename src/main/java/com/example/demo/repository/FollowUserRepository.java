package com.example.demo.repository;

import com.example.demo.domain.model.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FollowUserRepository extends JpaRepository<UserFollow, UUID> {
    <T> List<T> findBy(Class<T> type);
    UserFollow findByFollower (UUID userid);


}
