package com.inventory.management.mapper;

import com.inventory.management.dto.category.CategoryCreateDto;
import com.inventory.management.dto.category.CategoryResponseDto;
import com.inventory.management.model.Category;

public class CategoryMapper {
    public static CategoryResponseDto toDto(Category category) {
        return new CategoryResponseDto(category.getId(), category.getName());
    }
}
