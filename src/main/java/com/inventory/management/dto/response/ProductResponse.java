package com.inventory.management.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(
        String id,
        String name,
        String description,
        String categoryId,
        String categoryName,
        BigDecimal price,
        String sku,
        String imageUrl,
        LocalDateTime createdAt
) {}
