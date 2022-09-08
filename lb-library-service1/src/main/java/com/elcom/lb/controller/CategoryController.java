package com.elcom.lb.controller;


import com.elcom.lb.model.Category;
import com.elcom.lb.repository.category.CategoryRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {
    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);


    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getAllCategory(@RequestParam(required = false) String categoryID) {
        try {
            List<Category> categorys = new ArrayList<Category>();
            if (categoryID == null)
                categoryRepository.findAll().forEach(categorys::add);
            else
                categoryRepository.findByCategoryName((categoryID)).forEach(categorys::add);
            if (categorys.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(categorys, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // xóa cateogy theo id
    @DeleteMapping("/category/{categoryID}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("categoryID") String categoryID) {
        try {
            categoryRepository.deleteById(categoryID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // xóa cateogy
    @DeleteMapping("/category")
    public ResponseEntity<HttpStatus> deleteAllCategory() {
        try {
            categoryRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // thêm category
//    @RequestMapping(value = "/category", method = RequestMethod.POST)
//    public ResponseEntity<Category> createCategory(@RequestBody Category category)
//    {
//        try
//        {
//            Category _category = categoryRepository.save(
//                    new Category(category.getCategoryID(),
//                            category.getCategory_name()));
//            return new ResponseEntity<>(_category, HttpStatus.OK);
//        }
//        catch (Exception e)
//        {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        try {
            Category _category = categoryRepository
                    .save(new Category(category.getCategoryID(), category.getCategoryName()));
            return new ResponseEntity<>(_category, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // update category
//    @RequestMapping(value = "/category", method = RequestMethod.PUT)
//    public ResponseEntity<Void> updatecategory(@RequestBody Category category,@PathVariable String categoryID)
//    {
//        Optional<Category> categoryOptional = categoryRepository.findById(categoryID);
//
//        if(categoryOptional.isEmpty())
//            return ResponseEntity.notFound().build();
//
//        category.setCategoryID(categoryID);
//
//        categoryRepository.save(category);
//
//        return ResponseEntity.noContent().build();
//    }
    @PutMapping("/category/{categoryID}")
    public ResponseEntity<Category> updateCategory(@PathVariable("categoryID") String categoryID, @RequestBody Category category) {
        Optional<Category> categoryData = categoryRepository.findById(categoryID);
        if (categoryData.isPresent()) {
            Category _category= categoryData.get();
            _category.setCategoryID(category.getCategoryID());
            _category.setCategoryName(category.getCategoryName());
            return new ResponseEntity<>(categoryRepository.save(_category), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
