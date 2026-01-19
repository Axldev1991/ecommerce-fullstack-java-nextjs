package com.ecommerce.api.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.api.entities.Category;
import com.ecommerce.api.repositories.CategoryRepository;
import com.ecommerce.api.services.ICategoryService;

@Service

public class CategoryServiceImpl implements ICategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }


    
}