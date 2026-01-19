package com.ecommerce.api.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.api.entities.Product;
import com.ecommerce.api.repositories.ProductRepository;
import com.ecommerce.api.services.IProductService;

@Service

public class ProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }


    
}