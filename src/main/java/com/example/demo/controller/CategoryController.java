package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CategoryController {
    CategoryService categoryService;

    @GetMapping
    ApiResponse<List<CategoryDto>> getCategories() {
        return ApiResponse.<List<CategoryDto>>builder()
                .result(categoryService.getCategories())
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<CategoryDto> getCategoryById(@PathVariable Long id) {
        return ApiResponse.<CategoryDto>builder()
                .result(categoryService.getCategoryById(id))
                .build();
    }

    @PostMapping
    ApiResponse<CategoryDto> createCategory(@RequestBody @Valid CategoryDto categoryDto) {
        return ApiResponse.<CategoryDto>builder()
                .result(categoryService.createCategory(categoryDto))
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        return ApiResponse.<CategoryDto>builder()
                .result(categoryService.updateCategory(id, categoryDto))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ApiResponse.<String>builder()
                .result("Category has been deleted")
                .build();
    }

    @GetMapping("/test")
    List<Category> test() {
        return categoryService.test();
    }
}
