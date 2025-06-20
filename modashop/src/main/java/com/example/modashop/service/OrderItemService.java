package com.example.modashop.service;

import com.example.modashop.entity.OrderItem;
import com.example.modashop.exception.EntityNotFoundException;
import com.example.modashop.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository repository;

    public List<OrderItem> getAllOrderItems() {
        return repository.findAll();
    }

    public OrderItem getOrderItemById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(OrderItem.class, id));
    }

    public List<OrderItem> getOrderItemsByOrder(Long orderId) {
        return repository.findByOrderId(orderId);
    }

    public OrderItem createOrderItem(OrderItem orderItem) {
        return repository.save(orderItem);
    }

    public OrderItem updateOrderItem(OrderItem newOrderItem, Long id) {
        return repository.findById(id)
                .map(orderItem -> {
                    orderItem.setQuantity(newOrderItem.getQuantity());
                    orderItem.setUnitPrice(newOrderItem.getUnitPrice());
                    orderItem.setOrder(newOrderItem.getOrder());
                    orderItem.setVariant(newOrderItem.getVariant());
                    return repository.save(orderItem);
                })
                .orElseThrow(() -> new EntityNotFoundException(OrderItem.class, id));
    }

    public String deleteOrderItem(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException(OrderItem.class, id);
        }
        repository.deleteById(id);
        return "Order item with id " + id + " has been deleted";
    }
}