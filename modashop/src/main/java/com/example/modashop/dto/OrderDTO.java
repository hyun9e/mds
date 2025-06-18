package com.example.modashop.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private LocalDateTime orderDate;
    private String status;
    private double totalAmount;
    private String shippingAddress;
    private Long userId;
    private List<OrderItemDTO> items;
}