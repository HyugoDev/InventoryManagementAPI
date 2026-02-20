package com.inventory.management.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InventoryRequest(
        @NotBlank(message = "El ID del producto es obligatorio")
        String productId,

        @NotNull(message = "La cantidad inicial es obligatoria")
        @Min(value = 0, message = "La cantidad no puede ser negativa")
        Integer quantity,

        @Min(value = 0, message = "El stock mínimo no puede ser negativo")
        Integer minStock,

        @NotBlank(message = "La ubicación en almacén es obligatoria")
        String location
) {}
