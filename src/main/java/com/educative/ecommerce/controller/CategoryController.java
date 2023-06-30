package com.educative.ecommerce.controller;

import com.educative.ecommerce.entity.Category;
import com.educative.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/")
    public ResponseEntity<List<Category>> getCategories(){
        List<Category> body = categoryService.listCategories();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category){
        if (Objects.nonNull(categoryService.readCategory(category.getCategoryName()))) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Category already exists"),HttpStatus.CONFLICT);
        }else {
            categoryService.createCategory(category);
            return new ResponseEntity<>(new ApiResponse(true, "Category created"),HttpStatus.CREATED);
        }
    }
    @PutMapping("/update/{categoryID}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryID") Integer categoryID, @RequestBody Category category){
        if (Objects.nonNull(categoryService.readCategory(categoryID))){
            categoryService.updateCategory(categoryID, category);
            return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Category updated"),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ApiResponse(false, "Category does not exist"),HttpStatus.NOT_FOUND);
        }
    }
}
