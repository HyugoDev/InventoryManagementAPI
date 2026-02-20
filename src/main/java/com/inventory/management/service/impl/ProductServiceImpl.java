package com.inventory.management.service.impl;



import com.inventory.management.dto.request.ProductRequest;
import com.inventory.management.dto.response.ProductResponse;
import com.inventory.management.exception.ResourceNotFoundException;
import com.inventory.management.mapper.ProductMapper;
import com.inventory.management.model.Category;
import com.inventory.management.model.Product;
import com.inventory.management.repository.CategoryRepository;
import com.inventory.management.repository.ProductRepository;
import com.inventory.management.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper mapper;

    @Transactional(readOnly = true)
    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Transactional
    public ProductResponse save(ProductRequest request) {
        Long categoryId = Long.valueOf(request.categoryId());
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));

        Product product = mapper.toEntity(request);
        product.setCategory(category);
        log.info("Creando nuevo producto con SKU: {}", request.sku());
        return mapper.toDto(productRepository.save(product));
    }

    @Transactional(readOnly = true)
    public ProductResponse findById(Long id) {
        return mapper.toDto(productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found " + id)));
    }

//
//    @Transactional
//    public ProductDTO update(Long id, ProductCreateRequest request) {
//        Product product = productRepository.findById(id).orElseThrow(() ->
//                        new ResourceNotFoundException("Product not found with id " + id)
//                );
//
//
//        product.setName(data.getName());
//        product.setDescription(data.getDescription());
//        product.setPrice(data.getPrice());
//
//
//        if (!product.getCategory().getId().equals(data.getCategoryId())) {
//            Category category = categoryService.getById(data.getCategoryId());
//            product.setCategory(category);
//        }
//
//        Product updated = productRepository.save(product);
//
//        return ProductMapper.toDto(updated);
//    }
//
//    @Override
//    @Transactional
//    public void deleteById(Long id) {
//        Product product = productRepository.findById(id).orElseThrow(
//                () -> new ResourceNotFoundException("Product not found " + id)
//        );
//         productRepository.delete(product);
//    }


}
