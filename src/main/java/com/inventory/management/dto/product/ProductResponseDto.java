package com.inventory.management.dto.product;

import com.inventory.management.dto.category.CategoryResponseDto;
import com.inventory.management.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ProductResponseDto {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private CategoryResponseDto category;
}
