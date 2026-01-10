package com.inventory.management.dto.inventory;

import com.inventory.management.model.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InventoryResponseDto {

    private Long id;

    private Product product;

    private int quantity;
}
