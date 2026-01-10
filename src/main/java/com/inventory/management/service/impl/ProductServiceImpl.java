package com.inventory.management.service.impl;


import com.inventory.management.dto.product.ProductCreateDto;
import com.inventory.management.dto.product.ProductResponseDto;
import com.inventory.management.exception.ResourceNotFoundException;
import com.inventory.management.mapper.ProductMapper;
import com.inventory.management.model.Category;
import com.inventory.management.model.Product;
import com.inventory.management.repository.ProductRepository;
import com.inventory.management.service.CategoryService;
import com.inventory.management.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryService categoryService;

    @Override
    @Transactional
    public ProductResponseDto create(ProductCreateDto data) {
        return ProductMapper.toDto(productRepository.save(
                Product.builder()
                        .name(data.getName())
                        .description(data.getDescription())
                        .price(data.getPrice())
                        .category(categoryService.getById(data.getCategoryId()))
                        .build()
        ));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDto getById(Long id) {
        return ProductMapper.toDto(
                productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found " + id)
                ));
    }

    @Override
    @Transactional(readOnly = true)
    public Product getEntityById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found " + id)
                );
    }

    @Override
    @Transactional
    public ProductResponseDto update(Long id, ProductCreateDto data) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id " + id)
                );


        product.setName(data.getName());
        product.setDescription(data.getDescription());
        product.setPrice(data.getPrice());


        if (!product.getCategory().getId().equals(data.getCategoryId())) {
            Category category = categoryService.getById(data.getCategoryId());
            product.setCategory(category);
        }

        Product updated = productRepository.save(product);

        return ProductMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product not found " + id)
        );
         productRepository.delete(product);
    }


}
