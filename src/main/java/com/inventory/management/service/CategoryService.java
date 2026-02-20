package com.inventory.management.service;


import com.inventory.management.dto.request.CategoryRequest;
import com.inventory.management.dto.response.CategoryResponse;
import org.springframework.data.domain.Page;


import java.util.List;

public interface CategoryService {
    List<CategoryResponse> findAll();
    CategoryResponse save(CategoryRequest category);

    List<CategoryResponse> search(String search);

    CategoryResponse getById(Long id);

    void delete(Long id);
}
