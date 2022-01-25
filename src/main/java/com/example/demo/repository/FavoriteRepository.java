package com.example.demo.repository;

import com.example.demo.domain.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FavoriteRepository extends JpaRepository<Favorite, UUID> {
    void deleteByAnimeid(UUID id);
    Favorite findByAnimeid(UUID id);
}
