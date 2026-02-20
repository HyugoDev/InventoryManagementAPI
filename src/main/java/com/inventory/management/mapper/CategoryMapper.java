package com.inventory.management.mapper;

import com.inventory.management.dto.request.CategoryRequest;
import com.inventory.management.dto.response.CategoryResponse;
import com.inventory.management.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponse toDto(Category category);

    // De Request a Entidad
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    Category toEntity(CategoryRequest request);
}
