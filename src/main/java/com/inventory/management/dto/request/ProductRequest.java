package com.inventory.management.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 3, max = 100)
        String name,

        String description,

        @NotBlank(message = "La categoría es obligatoria")
        String categoryId,

        @NotNull(message = "El precio es obligatorio")
        @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
        BigDecimal price,

        @Pattern(regexp = "^[A-Z0-9-]+$", message = "Formato de SKU inválido")
        String sku,

        String imageUrl
) {}
