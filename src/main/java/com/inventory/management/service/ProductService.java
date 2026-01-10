package com.inventory.management.service;

import com.inventory.management.dto.product.ProductCreateDto;
import com.inventory.management.dto.product.ProductResponseDto;
import com.inventory.management.model.Product;
import java.util.List;

public interface ProductService {
    ProductResponseDto create(ProductCreateDto product);
    List<ProductResponseDto> getAllProducts();
    ProductResponseDto getById(Long id);
    Product getEntityById(Long id);
    ProductResponseDto update(Long id, ProductCreateDto product);
    void deleteById(Long id);
}
