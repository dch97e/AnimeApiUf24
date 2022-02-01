package com.example.demo.repository;

import com.example.demo.domain.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ImagesRepository extends JpaRepository<Image, UUID> {
    <T> List<T> findBy(Class<T> type);

    Image findByImageid(UUID id);
}
