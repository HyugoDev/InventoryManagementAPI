package com.inventory.management.service;

import com.inventory.management.model.Category;
import com.inventory.management.model.Inventory;

import java.util.List;

public interface InventoryService {
    Inventory create(Inventory inventory);
    Inventory update(Inventory inventory);
    void delete(Long inventoryId);
    Inventory findById(Long inventoryId);
    List<Inventory> findAll();
    List<Inventory> findByCategory(Category category);
}
