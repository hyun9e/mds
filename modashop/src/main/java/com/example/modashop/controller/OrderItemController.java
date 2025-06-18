package com.example.modashop.controller;

import com.example.modashop.entity.OrderItem;
import com.example.modashop.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
@RequestMapping("/order-items")
public class OrderItemController {
    private final OrderItemService service;

    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return service.getAllOrderItems();
    }

    @GetMapping("/{id}")
    public OrderItem getOrderItemById(@PathVariable Long id) {
        return service.getOrderItemById(id);
    }

    @GetMapping("/order/{orderId}")
    public List<OrderItem> getOrderItemsByOrder(@PathVariable Long orderId) {
        return service.getOrderItemsByOrder(orderId);
    }

    @PostMapping
    public OrderItem createOrderItem(@RequestBody OrderItem orderItem) {
        return service.createOrderItem(orderItem);
    }

    @PutMapping("/{id}")
    public OrderItem updateOrderItem(@RequestBody OrderItem orderItem, @PathVariable Long id) {
        return service.updateOrderItem(orderItem, id);
    }

    @DeleteMapping("/{id}")
    public String deleteOrderItem(@PathVariable Long id) {
        return service.deleteOrderItem(id);
    }
}