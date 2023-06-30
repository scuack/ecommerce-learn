package com.educative.ecommerce.controller;

import com.educative.ecommerce.dto.ProductDto;
import com.educative.ecommerce.entity.Category;
import com.educative.ecommerce.service.CategoryService;
import com.educative.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getProduct(){
        List<ProductDto> productDtos = productService.listProducts();
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto){
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        if (!optionalCategory.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false,"Category is invalid"), HttpStatus.CONFLICT);
        }else {
            Category category = optionalCategory.get();
            productService.addProduct(productDto, category);
            return new ResponseEntity<>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
        }
    }

    @PostMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Integer productId, @RequestBody ProductDto productDto){
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        if (!optionalCategory.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false, "Category is invalid"), HttpStatus.CONFLICT);
        }else {
            Category category = optionalCategory.get();
            productService.updateProduct(productId, productDto, category);
            return new ResponseEntity<>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
        }
    }
}
