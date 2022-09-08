package com.elcom.lb.repository.category;

import com.elcom.lb.model.Author;
import com.elcom.lb.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository  extends PagingAndSortingRepository<Category, String> {
    List<Category> findByCategoryName(String categoryName);
}
