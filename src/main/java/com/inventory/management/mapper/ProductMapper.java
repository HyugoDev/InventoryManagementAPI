package com.inventory.management.mapper;

import com.inventory.management.dto.request.ProductRequest;
import com.inventory.management.dto.response.ProductResponse;
import com.inventory.management.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    ProductResponse toDto(Product product);

    // De Request a Entidad (Entrada)
    // Ignoramos id y createdAt porque se generan en la DB
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true) // Se asigna manualmente en el Service
    Product toEntity(ProductRequest request);
}
