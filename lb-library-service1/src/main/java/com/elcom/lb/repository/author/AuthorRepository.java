package com.elcom.lb.repository.author;

import com.elcom.lb.model.Author;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends PagingAndSortingRepository<Author, String> {
    List<Author> findByAuthorName(String authorName);

}
