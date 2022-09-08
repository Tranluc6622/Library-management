package com.elcom.lb.repository.category;

import com.elcom.lb.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends PagingAndSortingRepository<Category, String> {
}
