package com.ecommerce.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.dtos.CategoryDTO;
import com.ecommerce.api.entities.Category;
import com.ecommerce.api.services.ICategoryService;

/**
 * REST Controller for managing product categories.
 * Provides endpoints for CRUD operations using CategoryDTO for data transfer.
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Creates a new category in the system.
     * 
     * @param categoryDTO The category data to persist.
     * @return ResponseEntity with the persisted category DTO and 201 status.
     */
    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        Category savedCategory = categoryService.save(category);

        CategoryDTO resultDTO = CategoryDTO.builder()
                .id(savedCategory.getId())
                .name(savedCategory.getName())
                .description(savedCategory.getDescription())
                .build();
        return new ResponseEntity<>(resultDTO, HttpStatus.CREATED);
    }

    /**
     * Retrieves all categories from the catalog.
     * 
     * @return ResponseEntity containing a list of CategoryDTOs.
     */
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<Category> categories = categoryService.findAll();
        List<CategoryDTO> listDTO = categories.stream()
                .map(cat -> CategoryDTO.builder()
                        .id(cat.getId())
                        .name(cat.getName())
                        .description(cat.getDescription())
                        .build())
                .toList();
        return ResponseEntity.ok(listDTO);
    }

    /**
     * Retrieves a specific category by its unique ID.
     * 
     * @param id The unique identifier of the category.
     * @return ResponseEntity with the category DTO or 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
        return ResponseEntity.ok(categoryDTO);
    }

    /**
     * Deletes a category from the database.
     * 
     * @param id The ID of the category to remove.
     * @return ResponseEntity with 204 No Content status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}