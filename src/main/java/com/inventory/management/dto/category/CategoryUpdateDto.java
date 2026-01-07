package com.inventory.management.dto.category;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryUpdateDto {
    @NotBlank(message = "Category name is required")
    private String name;
}
