package com.grocery.grocery_app.service;

import com.grocery.grocery_app.model.Category;
import com.grocery.grocery_app.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category addCategory(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new RuntimeException("Category already exists!");
        }
        return categoryRepository.save(category);

    }

        public Category updateCategory (Long id, Category updated){
            Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Category not found!"));
            category.setName(updated.getName());
            category.setDescription(updated.getDescription());
            category.setImageUrl(updated.getImageUrl());
            return categoryRepository.save(category);
        }

        public void deleteCategory (Long id){
            categoryRepository.deleteById(id);
        }
    }

