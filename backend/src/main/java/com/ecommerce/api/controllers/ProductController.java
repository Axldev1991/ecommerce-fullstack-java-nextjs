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

import com.ecommerce.api.dtos.ProductDTO;
import com.ecommerce.api.entities.Category;
import com.ecommerce.api.entities.Product;
import com.ecommerce.api.services.ICategoryService;
import com.ecommerce.api.services.IProductService;

/**
 * REST Controller for managing e-commerce products.
 * Provides endpoints for product lookup, creation, and deletion using DTOs.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final IProductService productService;
    private final ICategoryService categoryService;

    public ProductController(IProductService productService, ICategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    /**
     * Retrieves all products available in the catalog.
     * 
     * @return ResponseEntity containing the list of all products as DTOs.
     */
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() {
        List<Product> products = productService.findAll();
        List<ProductDTO> listDTO = products.stream()
                .map(prod -> ProductDTO.builder()
                        .id(prod.getId())
                        .name(prod.getName())
                        .description(prod.getDescription())
                        .price(prod.getPrice())
                        .stock(prod.getStock())
                        .categoryId(prod.getCategory().getId())
                        .categoryName(prod.getCategory().getName())
                        .build())
                .toList();
        return ResponseEntity.ok(listDTO);
    }

    /**
     * Retrieves detailed information for a single product.
     * 
     * @param id The unique product identifier.
     * @return ResponseEntity with the product DTO or 404 status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        ProductDTO dto = ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .build();
        return ResponseEntity.ok(dto);
    }

    /**
     * Registers a new product in the system.
     * 
     * @param productDTO The product details to save.
     * @return ResponseEntity with the persisted product DTO and 201 status.
     */
    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO) {
        Category category = categoryService.findById(productDTO.getCategoryId());

        if (category == null) {
            return ResponseEntity.badRequest().build();
        }

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setCategory(category);

        Product savedProduct = productService.save(product);

        ProductDTO resultDTO = ProductDTO.builder()
                .id(savedProduct.getId())
                .name(savedProduct.getName())
                .description(savedProduct.getDescription())
                .price(savedProduct.getPrice())
                .stock(savedProduct.getStock())
                .categoryId(category.getId())
                .categoryName(category.getName())
                .build();

        return new ResponseEntity<>(resultDTO, HttpStatus.CREATED);
    }

    /**
     * Removes a product from the database.
     * 
     * @param id The ID of the product to remove.
     * @return ResponseEntity with 204 No Content status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
