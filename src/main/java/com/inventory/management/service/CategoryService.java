package com.inventory.management.service;

import com.inventory.management.dto.category.CategoryCreateDto;
import com.inventory.management.dto.category.CategoryResponseDto;
import com.inventory.management.model.Category;

import java.util.List;

public interface CategoryService {
    CategoryResponseDto create(CategoryCreateDto category);

    List<CategoryResponseDto> getAll();
    Category getById(Long id);

    void delete(Long id);
}
