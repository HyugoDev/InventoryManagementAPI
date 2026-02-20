package com.inventory.management.service;

import com.inventory.management.dto.request.InventoryRequest;
import com.inventory.management.dto.response.InventoryResponse;
import java.util.List;

public interface InventoryService {
    List<InventoryResponse> findAll();
    InventoryResponse addStock(InventoryRequest inventory);
    InventoryResponse getById(Long inventoryId);
    InventoryResponse updateQuantity(Long Id, Integer quantity);
}
