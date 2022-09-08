package com.elcom.service;

import com.elcom.model_dto.CountBookByAuthor;
import com.elcom.model_dto.CountBookByCategory;
import com.elcom.model_dto.CountBookByFirstLetter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements  BookService{

    @Override
    public List<CountBookByAuthor> countBookByAuthor() {
        return List<CountBookByAuthor>
    }

    @Override
    public List<CountBookByCategory> countBookByCategory() {
        return null;
    }

    @Override
    public List<CountBookByFirstLetter> countBookByFirstLetter() {
        return null;
    }
}
