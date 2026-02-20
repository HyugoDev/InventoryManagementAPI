package com.inventory.management.service.impl;


import com.inventory.management.dto.request.InventoryRequest;
import com.inventory.management.dto.response.InventoryResponse;
import com.inventory.management.exception.ResourceNotFoundException;
import com.inventory.management.mapper.InventoryMapper;
import com.inventory.management.model.InventoryItem;
import com.inventory.management.model.Product;
import com.inventory.management.repository.InventoryItemRepository;
import com.inventory.management.repository.ProductRepository;
import com.inventory.management.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final InventoryItemRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final InventoryMapper mapper;


    @Override
    public InventoryResponse addStock(InventoryRequest request) {
        Long productId = Long.valueOf(request.productId());
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
        InventoryItem item = mapper.toEntity(request);
        item.setProduct(product);
        log.info("Actualizando inventario para el producto: {}", product.getName());
        return mapper.toDto(inventoryRepository.save(item));
    }

    @Override
    public InventoryResponse getById(Long inventoryId) {
        return null;
    }

    @Override
    public InventoryResponse updateQuantity(Long id, Integer quantity) {
        InventoryItem item = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item de inventario no encontrado"));

        item.setQuantity(quantity);

        return mapper.toDto(inventoryRepository.save(item));
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponse> findAll() {
       return inventoryRepository.findAll()
                       .stream()
                       .map(mapper::toDto)
                       .toList();
    }


}
