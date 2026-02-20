package com.inventory.management.repository;

import com.inventory.management.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;



public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
}
