package com.elcom.lb.repository.Book;

import com.elcom.lb.model.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, UUID> {
}
