package com.inventory.management.service.impl;

import com.inventory.management.dto.category.CategoryCreateDto;
import com.inventory.management.exception.ResourceNotFoundException;
import com.inventory.management.model.Category;
import com.inventory.management.repository.CategoryRepository;
import com.inventory.management.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public Category create(CategoryCreateDto data){
        return categoryRepository.save(
                Category.builder()
                        .name(data.getName())
                        .build()
        );
    }

    @Override
    public boolean existebyId(Long id){
        return categoryRepository.existsById(id);
    }

    @Override
    public Category findById(Long id) {
      return categoryRepository.findById(id)
              .orElseThrow(()-> new ResourceNotFoundException("Category not found " + id ));
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        if (categoryRepository.findById(id).isEmpty()) {
            throw  new ResourceNotFoundException("Category not found " + id );
        }
        categoryRepository.deleteById(id);
    }

}
