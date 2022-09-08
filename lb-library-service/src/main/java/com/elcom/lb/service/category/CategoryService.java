package com.elcom.lb.service.category;

import com.elcom.lb.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category>findAll(Integer currentPage, Integer rowPerPage, String sort);

    Category findById(String categoryID);

    void save(Category category);

    void remove(Category category);

    boolean insertTest();
}
