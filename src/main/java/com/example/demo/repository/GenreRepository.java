package com.example.demo.repository;

import com.example.demo.domain.model.Genre;
import com.example.demo.domain.model.projections.ProjectionGenre2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GenreRepository extends JpaRepository<Genre, UUID> {
   <T>List<T> findBy(Class<T> type);
   ProjectionGenre2 findByGenreid(UUID id);

}
