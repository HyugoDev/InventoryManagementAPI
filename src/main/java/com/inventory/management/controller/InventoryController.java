package com.inventory.management.controller;


import com.inventory.management.dto.inventory.InventoryCreateDto;
import com.inventory.management.dto.inventory.InventoryResponseDto;

import com.inventory.management.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryResponseDto> create(@Valid @RequestBody InventoryCreateDto inventory) {
        return ResponseEntity.ok(inventoryService.create(inventory));
    }

    @GetMapping
    public ResponseEntity<List<InventoryResponseDto>> getAll() {
        return ResponseEntity.ok(inventoryService.getAll());
    }




}
