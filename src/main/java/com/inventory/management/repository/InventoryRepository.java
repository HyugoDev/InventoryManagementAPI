package com.inventory.management.repository;

import com.inventory.management.model.Category;
import com.inventory.management.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByProductId(Long productId);
    List<Inventory> findByProductCategory(Category category);
}
