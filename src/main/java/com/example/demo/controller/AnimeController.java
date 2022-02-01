package com.example.demo.controller;

import com.example.demo.domain.dto.*;
import com.example.demo.domain.model.Anime;
import com.example.demo.domain.model.BlockAnime;
import com.example.demo.domain.model.Image;
import com.example.demo.domain.model.User;
import com.example.demo.domain.model.projections.*;
import com.example.demo.repository.AnimeRepository;
import com.example.demo.repository.BlockAnimeRepository;
import com.example.demo.repository.ImagesRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/animes")
public class AnimeController {

    @Autowired
    private AnimeRepository animeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlockAnimeRepository blockAnimeRepository;

    @Autowired
    private ImagesRepository imagesRepository;


    @GetMapping("/")
    public ResponseEntity<?> findallAnimes(Authentication authentication) {     //usuario autenticado que ha hecho la request
        if (authentication == null) {
            return ResponseEntity.ok().body(ListResult.list(animeRepository.findBy(ProjectionAnimeGenres.class)));
        }
        User authenticatedUser = userRepository.findByUsername(authentication.getName());

        return ResponseEntity.ok().body(ListResult.list(animeRepository.getAnimesforUser(authenticatedUser.userid, ProjectionAnimeBlock.class)));
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findAnimeByID(Authentication authentication, @PathVariable UUID id) {
        ProjectionAnimeGenres result = animeRepository.findByAnimeid(id, ProjectionAnimeGenres.class);
        if (result != null) {
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.message("No s'ha trobat l'anime amd id '" + id + "'"));
    }


    @PostMapping("/")
    public ResponseEntity<?> createAnime(@RequestBody Anime anime, Authentication authentication) {
        if (animeRepository.findByName(anime.name) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorMessage.message("Ja existeix un anime amb el nom '" + anime.name + "'"));
        }
        return ResponseEntity.ok().body(animeRepository.save(anime));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnimeById(Authentication authentication, @PathVariable UUID id) {
        Anime animeFound = animeRepository.findById(id).orElse(null);
        if (animeFound != null) {
            animeRepository.delete(animeFound);
            return ResponseEntity.ok().body(ErrorMessage.message("S'ha eliminat l'anime amb id '" + id + "'"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.message("No s'ha trobat l'anime amb id '" + id + "'"));
    }


    @GetMapping("/block")
    public ResponseEntity<?> findBlock(Authentication authentication) {     //usuario autenticado que ha hecho la request
        if (authentication != null) {
            User authentictedUser = userRepository.findByUsername(authentication.getName());
            if (authentictedUser != null) {
                return ResponseEntity.ok().body(ListResult.list(userRepository.findByUserid(authentictedUser.userid, ProjectionBlock.class)));
            }
        }
        return ResponseEntity.ok().body(ErrorMessage.message("No tens animes bloquejats"));
    }


   @PostMapping ("/block")
    public ResponseEntity<?> blockAnime(@RequestBody RequestBlockAnime requestBlockAnime, Authentication authentication){
       if (authentication != null) {
           User authenticatedUser = userRepository.findByUsername(authentication.getName());
           if (authenticatedUser != null) {
               BlockAnime blockAnime = new BlockAnime();
               blockAnime.animeid = requestBlockAnime.animeid;
               blockAnime.userid = authenticatedUser.userid;
               blockAnimeRepository.save(blockAnime);
               return ResponseEntity.ok().body(blockAnime);
           }
       }
       return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorMessage.message("No autorizado"));
   }


    @DeleteMapping ("/block")
    public ResponseEntity<?> deleteBlockAnime(@RequestBody RequestBlockAnime requestBlockAnime, Authentication authentication){
        if (authentication != null) {
            User authenticatedUser = userRepository.findByUsername(authentication.getName());
            if (authenticatedUser != null) {
                BlockAnime blockAnime = blockAnimeRepository.findByAnimeid(requestBlockAnime.animeid);
                blockAnimeRepository.delete(blockAnime);
                return ResponseEntity.ok().body(ErrorMessage.message(" S'ha eliminat dels bloquejats l'anime amd id'" + requestBlockAnime.animeid + "'"));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorMessage.message("No autorizado"));
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<?> findAnimeImages(Authentication authentication, @PathVariable UUID id) {
        ProjectionAnimeImages result = animeRepository.findByAnimeid(id,ProjectionAnimeImages.class);
        if (result != null) {
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessage.message("No s'ha trobat l'anime amd id '" + id + "'"));
    }
    @GetMapping("/images/")
    public ResponseEntity<?> findImagers() {
        return ResponseEntity.ok().body(ListResult.list(imagesRepository.findBy(ProjectionImages.class)));
    }
    @PostMapping ("/images/")
    public ResponseEntity<?> imageAnime(@RequestBody RequestImage requestImage, Authentication authentication){
        if (authentication != null) {
            User authenticatedUser = userRepository.findByUsername(authentication.getName());
            if (authenticatedUser != null) {
                Anime anime = animeRepository.findById(requestImage.animeid).orElse(null);
                if (anime ==null){
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorMessage.message("No existeix l'anime amb id'" + requestImage.animeid + "'"));
                }
                Image images = new Image();
                images.imageurl=requestImage.imageurl;
                images.animeid =requestImage.animeid;
                imagesRepository.save(images);
                return ResponseEntity.ok().body(imagesRepository.save(images));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorMessage.message("No autorizado"));
    }
    @DeleteMapping ("/images/")
    public ResponseEntity<?> deleteImaeg(@RequestBody RequestImageDelete requestImageDelete, Authentication authentication){
        if (authentication != null) {
            User authenticatedUser = userRepository.findByUsername(authentication.getName());
            if (authenticatedUser != null) {
                Image image = imagesRepository.findByImageid(requestImageDelete.imageid);
                if (image ==null){
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorMessage.message("No existeix la imatge amb id'" + requestImageDelete.imageid + "'"));
                }

                imagesRepository.delete(image);
                return ResponseEntity.ok().body(ErrorMessage.message(" S'ha eliminat la imatge amd id'" + requestImageDelete.imageid + "'"));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorMessage.message("No autorizado"));
    }



}
