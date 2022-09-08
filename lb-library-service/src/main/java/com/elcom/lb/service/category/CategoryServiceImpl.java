package com.elcom.lb.service.category;

import com.elcom.lb.model.Category;
import com.elcom.lb.repository.category.CategoryCustomizeRepository;
import com.elcom.lb.repository.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryCustomizeRepository categoryCustomizeRepository;

    @Override
    public List<Category> findAll(Integer currentPage, Integer rowsPerPage, String sort) {
        {
            if (currentPage > 0) currentPage--;

            Pageable paging = PageRequest.of(currentPage, rowsPerPage, Sort.by(sort).descending());

            Page<Category> pagedResult = categoryRepository.findAll(paging);

            if (pagedResult.hasContent())
                return pagedResult.getContent();
            else
                return new ArrayList<>();
        }
    }
    @Override
    public Category findById(String categoryID)
    {
        return categoryCustomizeRepository.findById(categoryID);
    }

    @Override
    public void save(Category category)
    {
        categoryRepository.save(category);
    }
    @Override
    public void remove(Category  category)
    {
        categoryRepository.delete(category);
    }
    @Override
    public boolean insertTest()
    {
        return categoryCustomizeRepository.insertTest();
    }

}
