package com.inventory.management.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequest(
        @NotBlank(message = "El nombre de la categoría es obligatorio")
        @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
        String name,

        @Size(max = 255, message = "La descripción no puede exceder los 255 caracteres")
        String description
) { }
