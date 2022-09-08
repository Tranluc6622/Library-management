package com.elcom.lb.service.author;

import com.elcom.lb.model.Author;
import com.elcom.lb.repository.author.AuthorCustomizeRepository;
import com.elcom.lb.repository.author.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    private static AuthorRepository authorRepository;

    @Autowired
    private AuthorCustomizeRepository authorCustomizeRepository;

    @Override
    public List<Author> findAll(Integer currentPage, Integer rowsPerPage, String sort) {
        {
            if (currentPage > 0) currentPage--;

            Pageable paging = PageRequest.of(currentPage, rowsPerPage, Sort.by(sort).descending());

            Page<Author> pagedResult = authorRepository.findAll(paging);

            if (pagedResult.hasContent())
                return pagedResult.getContent();
            else
                return new ArrayList<>();
        }
    }
    @Override
    public Author findById(String authorID)
    {
        return authorCustomizeRepository.findById(authorID);
    }

    @Override
    public Author save(Author author)
    {
        return authorRepository.save(author);

    }
    @Override
    public void remove(Author author)
    {
        authorRepository.delete(author);
    }
    @Override
    public void insertTest(Author author)
    {
        authorCustomizeRepository.insertTest(author);
    }
}
