package com.inventory.management.service.impl;


import com.inventory.management.dto.request.CategoryRequest;
import com.inventory.management.dto.response.CategoryResponse;

import com.inventory.management.exception.ResourceNotFoundException;
import com.inventory.management.mapper.CategoryMapper;
import com.inventory.management.model.Category;
import com.inventory.management.repository.CategoryRepository;
import com.inventory.management.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;


    @Override
    @Transactional
    public CategoryResponse save(CategoryRequest request) {
        Category category = mapper.toEntity(request);
        return mapper.toDto(repository.save(category));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse getById(Long id){
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id)));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Eliminando categoría con id: {}", id);
        // 1. Verificar si existe antes de intentar borrar
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar: Categoría no encontrada con id " + id);
        }

        // 2. Ejecutar la eliminación
        repository.deleteById(id);

        log.info("Categoría con id {} eliminada exitosamente", id);
    }


    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> search(String search) {
        return repository.search(search, PageRequest.of(0, 20))
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
