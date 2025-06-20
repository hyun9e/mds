package com.example.modashop.controller;

import com.example.modashop.dto.CartDTO;
import com.example.modashop.entity.Cart;
import com.example.modashop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<?> getAllCarts() {
        return ResponseEntity.ok(cartService.getAllCarts());
    }


    @GetMapping("/{userId}")
    public ResponseEntity<CartDTO> getCartDTOByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartDTOByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<?> addToCart(
            @RequestParam Long userId,
            @RequestParam Long variantId,
            @RequestParam int quantity) {
        cartService.addItemToCart(userId, variantId, quantity);
        return ResponseEntity.ok("Added to cart");
    }

    @PutMapping
    public ResponseEntity<?> updateQuantity(
            @RequestParam Long itemId,
            @RequestParam int quantity) {
        cartService.updateItemQuantity(itemId, quantity);
        return ResponseEntity.ok("Quantity updated");
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> removeItem(@PathVariable Long itemId) {
        cartService.removeItem(itemId);
        return ResponseEntity.ok("Item removed");
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok("Cart cleared");
    }
}
