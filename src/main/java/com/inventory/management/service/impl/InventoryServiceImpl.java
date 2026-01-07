package com.inventory.management.service.impl;

import com.inventory.management.exception.ResourceNotFoundException;
import com.inventory.management.model.Category;
import com.inventory.management.model.Inventory;
import com.inventory.management.repository.InventoryRepository;
import com.inventory.management.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private InventoryRepository inventoryRepository;


    @Override
    public Inventory create(Inventory inventory) {
        return inventoryRepository.save(inventory);
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
    public Inventory findById(Long inventoryId) {
        return inventoryRepository.findById(inventoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Inventory not found " + inventoryId ));
    }

    @Override
    public List<Inventory> findAll() {
       return inventoryRepository.findAll();
    }

    @Override
    public List<Inventory> findByCategory(Category category) {
       return inventoryRepository.findByProductCategory(category);
    }
}
