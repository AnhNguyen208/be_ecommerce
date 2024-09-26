package com.example.demo.service;

import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.Category;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    public List<CategoryDto> getCategories() {
        return  categoryRepository.findAll().stream()
                .map(categoryMapper::toCategoryDto).toList();
    }

    public CategoryDto getCategoryById(Long id) {
        return categoryMapper.toCategoryDto(
                categoryRepository.findById(id).orElseThrow(RuntimeException::new
                ));
    }

    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = categoryMapper.toCategory(categoryDto);
        category = categoryRepository.save(category);
        return categoryMapper.toCategoryDto(category);
    }

    public CategoryDto updateCategory(Long id, CategoryDto categoryDto){
        Category category = categoryMapper.toCategory(categoryDto);
        category.setId(id);
        category = categoryRepository.save(category);

        return categoryMapper.toCategoryDto(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public List<Category> test() {
        return  categoryRepository.findAll();
    }
}
