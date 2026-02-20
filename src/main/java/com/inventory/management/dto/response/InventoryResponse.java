package com.inventory.management.dto.response;

import java.time.LocalDateTime;

public record InventoryResponse(
        String id,
        String productId,
        String productName, // Para evitar que el Front haga otro fetch
        Integer quantity,
        Integer minStock,
        String location,
        LocalDateTime lastUpdated
) {}
