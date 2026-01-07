package com.inventory.management.service;

import com.inventory.management.model.Product;
import java.util.List;

public interface ProductService {
    Product create(Product product);
    List<Product> findAll();
    Product findById(Long id);
}
