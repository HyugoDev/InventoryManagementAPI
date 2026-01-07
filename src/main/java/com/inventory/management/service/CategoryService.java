package com.inventory.management.service;

import com.inventory.management.dto.category.CategoryCreateDto;
import com.inventory.management.model.Category;

import java.util.List;

public interface CategoryService {
    Category create(CategoryCreateDto category);

    boolean existebyId(Long id);

    Category findById(Long id);
    List<Category> findAll();
    void delete(Long id);
}
