package com.inventory.management.model;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @Entity
@Table(name = "inventory_items")
public class InventoryItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "min_stock")
    private Integer minStock;

    private String location;
}