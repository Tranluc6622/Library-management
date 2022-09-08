package com.elcom.lb.controller;

import com.elcom.lb.model.Author;

import com.elcom.lb.repository.author.AuthorRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController
public class AuthorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    private AuthorRepository authorRepository;
    @GetMapping("/author")
    public ResponseEntity<List<Author>> getAllAuthor(@RequestParam(required = false) String authorID) {
        try {
            List<Author> authors = new ArrayList<Author>();
            if (authorID == null)
                authorRepository.findAll().forEach(authors::add);
            else
                authorRepository.findByAuthorName((authorID)).forEach(authors::add);
            if (authors.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(authors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/author")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        try {
            Author _author = authorRepository
                    .save(new Author(author.getAuthorID(), author.getAuthorName()));
            return new ResponseEntity<>(_author, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
