package com.inventory.management.service.impl;

import com.inventory.management.dto.inventory.InventoryCreateDto;
import com.inventory.management.dto.inventory.InventoryResponseDto;
import com.inventory.management.exception.ResourceNotFoundException;
import com.inventory.management.mapper.InventoryMapper;
import com.inventory.management.model.Category;
import com.inventory.management.model.Inventory;
import com.inventory.management.repository.InventoryRepository;
import com.inventory.management.service.InventoryService;
import com.inventory.management.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private InventoryRepository inventoryRepository;
    private ProductService productService;


    @Override
    public InventoryResponseDto create(InventoryCreateDto inventory) {
        return InventoryMapper.toDto(
                inventoryRepository.save(
                        Inventory.builder()
                                .product(productService.getEntityById(inventory.getProductId()))
                                .quantity(inventory.getQuantity())
                                .build()
                )
        );
    }

    @Override
    public Inventory update(Inventory inventory) {
        if (inventoryRepository.findById(inventory.getId()).isEmpty()) {
            throw  new ResourceNotFoundException("Inventory not found");
        }

        return inventoryRepository.save(inventory);
    }

    @Override
    public void delete(Long inventoryId) {
        if (inventoryRepository.findById(inventoryId).isEmpty()) {
            throw  new ResourceNotFoundException("Inventory not found " + inventoryId );
        }
        inventoryRepository.deleteById(inventoryId);
    }

    @Override
    public Inventory getById(Long inventoryId) {
        return inventoryRepository.findById(inventoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Inventory not found " + inventoryId ));
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponseDto> getAll() {
       return inventoryRepository.findAll()
                       .stream()
                       .map(InventoryMapper::toDto)
                       .toList();
    }

    @Override
    public List<Inventory> findByCategory(Category category) {
       return inventoryRepository.findByProductCategory(category);
    }
}
