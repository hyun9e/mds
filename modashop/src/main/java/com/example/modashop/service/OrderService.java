package com.example.modashop.service;

import com.example.modashop.entity.Order;
import com.example.modashop.exception.EntityNotFoundException;
import com.example.modashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Order getOrderById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product", id));
    }

    public List<Order> getOrdersByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    public Order createOrder(Order order) {
        return repository.save(order);
    }

    public Order updateOrder(Order newOrder, Long id) {
        return repository.findById(id)
                .map(order -> {
                    order.setStatus(newOrder.getStatus());
                    order.setTotalAmount(newOrder.getTotalAmount());
                    order.setShippingAddress(newOrder.getShippingAddress());
                    return repository.save(order);
                })
                .orElseThrow(() -> new EntityNotFoundException("Order", id));
    }

    public String deleteOrder(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Order", id);
        }
        repository.deleteById(id);
        return "Order with id " + id + " has been deleted";
    }
}