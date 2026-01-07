package com.inventory.management.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryCreateDto {
    @NotBlank(message = "Category name is required")
    private String name;
}
