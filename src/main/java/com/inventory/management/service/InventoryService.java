package com.inventory.management.service;

import com.inventory.management.dto.inventory.InventoryCreateDto;
import com.inventory.management.dto.inventory.InventoryResponseDto;
import com.inventory.management.model.Category;
import com.inventory.management.model.Inventory;
import jakarta.validation.Valid;

import java.util.List;

public interface InventoryService {
    InventoryResponseDto create(InventoryCreateDto inventory);
    Inventory update(Inventory inventory);
    void delete(Long inventoryId);
    Inventory getById(Long inventoryId);
    List<InventoryResponseDto> getAll();
    List<Inventory> findByCategory(Category category);
}
