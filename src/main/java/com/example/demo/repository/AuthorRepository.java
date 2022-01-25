package com.example.demo.repository;

import com.example.demo.domain.model.Author;
import com.example.demo.domain.model.projections.ProjectionAuthorGenres;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
   <T>List<T> findBy(Class<T> type);
   ProjectionAuthorGenres findByAuthorid(UUID id);

}
