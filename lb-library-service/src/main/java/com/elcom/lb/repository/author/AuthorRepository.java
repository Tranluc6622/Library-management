package com.elcom.lb.repository.author;

import com.elcom.lb.model.Author;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends PagingAndSortingRepository<Author, String> {

}
