package com.ecommerce.api.services;

import java.util.List;
import com.ecommerce.api.entities.Category;

/**
 * Service interface for Category domain operations.
 * Defines the contract for business logic related to product categories.
 */
public interface ICategoryService {

    /**
     * Retrieves all categories.
     * 
     * @return List of all categories.
     */
    List<Category> findAll();

    /**
     * Finds a category by its unique ID.
     * 
     * @param id Category ID.
     * @return The Category if found, null otherwise.
     */
    Category findById(Long id);

    /**
     * Saves or updates a category.
     * 
     * @param category Category data to persist.
     * @return The persisted category instance.
     */
    Category save(Category category);

    /**
     * Deletes a category from the database.
     * 
     * @param id ID of the category to remove.
     */
    void delete(Long id);
}
