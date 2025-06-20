package com.example.modashop.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private double price;

    private String imageUrl;

    @NotNull
    private Long categoryId;

    private double discount = 0;
}