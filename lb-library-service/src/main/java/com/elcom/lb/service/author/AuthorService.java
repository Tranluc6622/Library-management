package com.elcom.lb.service.author;

import com.elcom.lb.model.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAll(Integer currentPage, Integer rowPerPage, String sort);
    Author findById(String authorID);

    Author save(Author author);
    void remove(Author author);
    void insertTest(Author author);


}
