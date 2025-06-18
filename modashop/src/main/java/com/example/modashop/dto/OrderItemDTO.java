package com.example.modashop.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long id;
    private int quantity;
    private double unitPrice;
    private Long productVariantId;
}