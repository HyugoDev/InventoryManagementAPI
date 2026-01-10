package com.inventory.management.service.impl;

import com.inventory.management.dto.category.CategoryCreateDto;
import com.inventory.management.dto.category.CategoryResponseDto;
import com.inventory.management.exception.ResourceNotFoundException;
import com.inventory.management.mapper.CategoryMapper;
import com.inventory.management.model.Category;
import com.inventory.management.repository.CategoryRepository;
import com.inventory.management.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    @Transactional
    public CategoryResponseDto create(CategoryCreateDto data){
        return CategoryMapper.toDto(
                categoryRepository.save(
                Category.builder()
                        .name(data.getName())
                        .build()
        ));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponseDto> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Category getById(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id)
        );
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Category category = getById(id);
        categoryRepository.delete(category);
    }

}
