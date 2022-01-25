package com.example.demo.repository;

import com.example.demo.domain.model.BlockAnime;
import com.example.demo.domain.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BlockAnimeRepository extends JpaRepository<BlockAnime, UUID> {
    void deleteByAnimeid(UUID id);

    <T> List<T> findBy(Class<T> type);

    BlockAnime findByAnimeid(UUID animeid);

    <T> List<T> findByUserid (UUID userid , Class<T> type);
}
