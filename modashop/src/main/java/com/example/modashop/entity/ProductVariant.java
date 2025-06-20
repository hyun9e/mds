package com.example.modashop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_variants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String size;

    private String color;

    @Min(0)
    @Column(nullable = false, columnDefinition = "default int 1")
    private int stock = 1;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}
