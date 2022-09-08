package com.elcom.lb.service.book;

import com.elcom.lb.model.Book;

import java.util.List;
import java.util.UUID;

public interface BookService {
    List<Book> findAll(Integer currentPage, Integer rowPerPage, String sort);

    Book findById(UUID bookID);

    List<Book> save(List<Book> book);

    void save(Book book);

    void remove(Book book);

    boolean insertTest();
}
