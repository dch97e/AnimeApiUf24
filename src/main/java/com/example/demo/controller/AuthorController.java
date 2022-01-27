package com.example.demo.controller;

import com.example.demo.domain.dto.ErrorMessage;

import com.example.demo.domain.dto.ListResult;
import com.example.demo.domain.model.Author;
import com.example.demo.domain.model.projections.ProjectionAuthor;
import com.example.demo.domain.model.projections.ProjectionAuthorGenres;
import com.example.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;


    @GetMapping("/")
    public ResponseEntity<?> findAllAuthors(Authentication authentication) {
        return ResponseEntity.ok().body(ListResult.list(authorRepository.findBy(ProjectionAuthor.class)));
    }


   @GetMapping("/{id}")
    public ResponseEntity<?> findAnimeByID(Authentication authentication, @PathVariable UUID id) {
        ProjectionAuthorGenres result = authorRepository.findByAuthorid(id);
        if (result != null) {
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.message("No s'ha trobat l'anime amd id '" + id + "'"));
    }


}
