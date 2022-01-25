package com.example.demo.controller;

import com.example.demo.domain.dto.ErrorMessage;
import com.example.demo.domain.dto.ListResult;
import com.example.demo.domain.dto.RequestFavorite;
import com.example.demo.domain.dto.UserRegisterRequest;

import com.example.demo.domain.model.Favorite;
import com.example.demo.domain.model.User;
import com.example.demo.domain.model.projections.ProjectionFavorites;
import com.example.demo.domain.model.projections.ProjectionUser;
import com.example.demo.repository.AnimeRepository;
import com.example.demo.repository.FavoriteRepository;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private AnimeRepository animeRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody UserRegisterRequest userRegisterRequest) {

        if (userRepository.findByUsername(userRegisterRequest.username) == null) {
            User user = new User();
            user.username = userRegisterRequest.username;
            user.password = passwordEncoder.encode(userRegisterRequest.password);
            user.enabled = true;
            userRepository.save(user);
            return "OK";
        }
        return "ERROR";
    }

    //Obté tots els usuaris

    @GetMapping("/")
    public ResponseEntity<?> getALl(){
        return ResponseEntity.ok().body(ListResult.list(userRepository.findBy(ProjectionUser.class)));
    }

    @GetMapping("/favorites")
    public ResponseEntity<?> getUser(Authentication authentication){
        if (authentication != null) {
            User authenticatedUser = userRepository.findByUsername(authentication.getName());

            if (authenticatedUser != null) {
                return ResponseEntity.ok().body(userRepository.findByUsername(authentication.getName(), ProjectionFavorites.class));
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorMessage.message("No autorizado"));
    }

    @PostMapping("/favorites")
    public ResponseEntity<?> addFavorite(@RequestBody RequestFavorite requestFavorite, Authentication authentication) {


        if (authentication != null) {
            User authenticatedUser = userRepository.findByUsername(authentication.getName());

            if (authenticatedUser != null) {
                    Favorite favorite = new Favorite();
                    favorite.animeid = requestFavorite.animeid;
                    favorite.userid = authenticatedUser.userid;
                    favoriteRepository.save(favorite);
                    return ResponseEntity.ok().body(favorite);
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorMessage.message("No autorizado"));

        //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorMessage.message("Not authorized"));

    }
    @DeleteMapping("/favorites")
    public ResponseEntity<?> delFavorite(@RequestBody RequestFavorite requestFavorite, Authentication authentication) {
        if (authentication != null) {
            User authenticatedUser = userRepository.findByUsername(authentication.getName());
            if (authenticatedUser != null) {
                    Favorite favorite = favoriteRepository.findByAnimeid(requestFavorite.animeid);
                    favoriteRepository.delete(favorite);
                    return ResponseEntity.ok().body(ErrorMessage.message(" S'ha eliminat dels favorits l'anime amd id'" + requestFavorite.animeid + "'"));
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorMessage.message("No autorizado"));
    }



    //Afegeix un usuari

    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (userRepository.findByUsername(user.username) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorMessage.message("Ja existeix un usuari amb el nom ' " + user.username + " '"));
        } else {
            return ResponseEntity.ok().body(userRepository.save(user));
        }
    }

    //Elimina un usuari pel seu ID

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable UUID id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
            return ResponseEntity.ok().body(ErrorMessage.message("S'ha eliminat l'usuari amd id '" + id + "'"));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(ErrorMessage.message("No s'ha trobat l'user amd id '" + id + "'"));
        }
    }

    // Elimina tots els usuaris

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() {
        userRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(ErrorMessage.message("S'han eliminat TOTS"));
    }


}
