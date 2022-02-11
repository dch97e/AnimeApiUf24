package com.example.demo.repository;


import com.example.demo.domain.dto.UserResult;
import com.example.demo.domain.model.User;
import com.example.demo.domain.model.projections.ProjectionUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);
    <T> List<T> findByUsername(String username, Class<T> type);
    <T> List<T> findBy(Class<T> type);
    <T> List<T> findByUserid(UUID id, Class<T> type);
    User findByUserid(UUID id);
}
