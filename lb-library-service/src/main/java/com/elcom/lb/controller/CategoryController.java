package com.elcom.lb.controller;

import com.elcom.lb.model.Author;
import com.elcom.lb.model.Category;
import com.elcom.lb.repository.category.CategoryCustomizeRepository;
import com.elcom.lb.repository.category.CategoryRepository;
import com.elcom.lb.service.category.CategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {
    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryCustomizeRepository categoryCustomizeRepository;

    @RequestMapping(value = "/find-category", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Lay danh sach Author", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Thành công, có dữ liệu"),
            @ApiResponse(code = 204, message = "Thành công, không có dữ liệu"),
            @ApiResponse(code = 401, message = "Chưa xác thực"),
            @ApiResponse(code = 403, message = "Truy cập bị cấm"),
            @ApiResponse(code = 404, message = "Không tìm thấy"),
            @ApiResponse(code = 500, message = "Lỗi BackEnd")
    })
    public ResponseEntity<List<Category>> findAll(@RequestParam(defaultValue = "1") Integer currentPage,
                                                  @RequestParam(defaultValue = "10") Integer rowsPerPage,
                                                  @RequestParam(defaultValue = "authorid") String sort) {
        List<   Category> categoryList = categoryCustomizeRepository.findAll();
        if (categoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
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
                    .save(new Category(category.getCategoryID(), category.getCategory_name()));
            return new ResponseEntity<>(_category, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // update category
//    @RequestMapping(value = "/author", method = RequestMethod.PUT)
//    public ResponseEntity<Void> updateAuthor(@RequestBody Category category,@PathVariable String categoryID)
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
            _category.setCategory_name(category.getCategory_name());
            return new ResponseEntity<>(categoryRepository.save(_category), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
