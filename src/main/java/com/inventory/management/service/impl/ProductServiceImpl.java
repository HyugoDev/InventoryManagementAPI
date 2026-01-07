package com.inventory.management.service.impl;


import com.inventory.management.exception.ResourceNotFoundException;
import com.inventory.management.model.Product;
import com.inventory.management.repository.ProductRepository;
import com.inventory.management.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;



    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found " + id));
    }
}
