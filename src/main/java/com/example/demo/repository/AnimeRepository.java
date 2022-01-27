package com.example.demo.repository;

import com.example.demo.domain.model.Anime;
import com.example.demo.domain.model.projections.ProjectionAnimeGenres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AnimeRepository extends JpaRepository<Anime, UUID> {
    Anime findByName(String name);
    ProjectionAnimeGenres findByAnimeid(UUID id);
    <T> List<T> findBy(Class<T> type);

    @Query("SELECT a.animeid as animeid, a.name as name, a.image as image FROM Anime a WHERE a.animeid NOT IN (SELECT b.animeid FROM BlockAnime b WHERE b.userid = ?1)")
    <T> List<T> getAnimesforUser(UUID userid, Class<T> clazz);
}
