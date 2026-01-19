package com.ecommerce.api.services;

import java.util.List;
import com.ecommerce.api.entities.Category;

public interface ICategoryService {

    List<Category> findAll();

    Category findById(Long id);

    Category save(Category category);

    void delete(Long id);
}
