package com.inventory.management.mapper;


import com.inventory.management.dto.product.ProductResponseDto;
import com.inventory.management.model.Product;

public class ProductMapper {
    public static ProductResponseDto toDto(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                CategoryMapper.toDto(product.getCategory())
        );
    }
}
