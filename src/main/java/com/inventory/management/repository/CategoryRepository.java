package com.inventory.management.repository;

import com.inventory.management.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository  extends JpaRepository<Category, Long> {
    @Query("""
SELECT c
FROM Category c
WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :q, '%'))
""")
    List<Category> search(@Param("q") String q, Pageable pageable);
}
