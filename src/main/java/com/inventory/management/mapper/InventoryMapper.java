package com.inventory.management.mapper;


import com.inventory.management.dto.inventory.InventoryResponseDto;
import com.inventory.management.model.Inventory;

public class InventoryMapper {
    public static InventoryResponseDto toDto(Inventory inventory) {
        return new InventoryResponseDto(inventory.getId(), inventory.getProduct(), inventory.getQuantity());
    }
}
