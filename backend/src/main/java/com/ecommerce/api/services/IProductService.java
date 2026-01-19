package com.ecommerce.api.services;

import java.util.List;
import com.ecommerce.api.entities.Product;

public interface IProductService {

    List<Product> findAll();

    Product findById(Long id);

    Product save(Product product);

    void delete(Long id);
}
