package com.example.demo.controller;

import com.example.demo.domain.dto.ErrorMessage;
import com.example.demo.domain.dto.FileResult;
import com.example.demo.domain.dto.ListResult;
import com.example.demo.domain.model.File;
import com.example.demo.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileRepository fileRepository;

    @PostMapping
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile uploadedFile) {
        try {
            File file = new File();
            file.contenttype = uploadedFile.getContentType();
            file.data = uploadedFile.getBytes();
            File savedFile = fileRepository.save(file);
            FileResult fileResult = new FileResult(savedFile.fileid, savedFile.contenttype);
            return ResponseEntity.ok().body(fileResult);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getFile(@PathVariable UUID id) {
        File file = fileRepository.findById(id).orElse(null);
        if (file == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorMessage.message("File not found"));
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(file.contenttype))
                .contentLength(file.data.length)
                .body(file.data);
    }


    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok().body(ListResult.list(fileRepository.findBy()));
    }


    @DeleteMapping ("/delete")
    public ResponseEntity<?> deleteAll (){
        fileRepository.deleteAll();
        return ResponseEntity.ok().body(ErrorMessage.message("All files delete"));
    }



}