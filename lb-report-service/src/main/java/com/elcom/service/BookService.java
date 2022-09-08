package com.elcom.service;

import com.elcom.model_dto.CountBookByAuthor;
import com.elcom.model_dto.CountBookByCategory;
import com.elcom.model_dto.CountBookByFirstLetter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<CountBookByAuthor> countBookByAuthor();

    List<CountBookByCategory> countBookByCategory();

    List<CountBookByFirstLetter> countBookByFirstLetter();
}
