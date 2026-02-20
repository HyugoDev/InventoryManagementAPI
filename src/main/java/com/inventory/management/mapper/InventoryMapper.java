package com.inventory.management.mapper;

import com.inventory.management.dto.request.InventoryRequest;
import com.inventory.management.dto.response.InventoryResponse;
import com.inventory.management.model.InventoryItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    InventoryResponse toDto(InventoryItem item);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true) // Se busca en el Service por ID
    InventoryItem toEntity(InventoryRequest request);
}
