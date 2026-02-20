package com.inventory.management.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;


@Getter @Setter @NoArgsConstructor @Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(unique = true)
    private String sku;

    @Column(name = "image_url")
    private String imageUrl;
}
