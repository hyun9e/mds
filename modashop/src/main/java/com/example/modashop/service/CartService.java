package com.example.modashop.service;

import com.example.modashop.dto.CartDTO;
import com.example.modashop.dto.CartItemDTO;
import com.example.modashop.entity.Cart;
import com.example.modashop.entity.CartItem;
import com.example.modashop.entity.ProductVariant;
import com.example.modashop.entity.User;
import com.example.modashop.repository.CartItemRepository;
import com.example.modashop.repository.CartRepository;
import com.example.modashop.repository.ProductVariantRepository;
import com.example.modashop.repository.UserRepository;
import com.example.modashop.util.EntityHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    private final UserRepository userRepository;

    private final ProductVariantRepository productVariantRepository;
    private final EntityHelper entityHelper;

    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    User user = entityHelper.findOrThrowException(User.class, userId, userRepository);
                    Cart cart = new Cart();
                    cart.setUser(user);
                    return cartRepository.save(cart);
                });
    }
    public CartDTO getCartDTOByUserId(Long userId) {
        Cart cart = getCartByUserId(userId);
        return convertToCartDTO(cart);
    }

    public void addItemToCart(Long userId, Long variantId, int quantity) {
        Cart cart = getCartByUserId(userId);
        ProductVariant variant = entityHelper.findOrThrowException(ProductVariant.class, variantId, productVariantRepository);

        CartItem existed = cart.getItems().stream().filter(item -> item.getProductVariant().getId().equals(variantId)).findFirst().orElse(null);

        if (existed != null) {
            existed.setQuantity(existed.getQuantity() + quantity);
        } else {
            CartItem newItem = CartItem.builder().productVariant(variant).quantity(quantity).cart(cart).build();
            cart.getItems().add(newItem);
        }

        cartRepository.save(cart);
    }


    public void updateItemQuantity(Long itemId, int quantity) {
        CartItem item = entityHelper.findOrThrowException(CartItem.class, itemId, cartItemRepository);
        item.setQuantity(quantity);
        cartItemRepository.save(item);
    }

    public void removeItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    public void clearCart(Long userId) {
        Cart cart = getCartByUserId(userId);
        cart.getItems().clear();
        cartRepository.save(cart);
    }


    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }


    private CartItemDTO convertToCartItemDTO(CartItem item) {
        return CartItemDTO.builder()
                .id(item.getId())
                .variantId(item.getProductVariant().getId())
                .quantity(item.getQuantity())
                .build();
    }
    private CartDTO convertToCartDTO(Cart cart) {
        return CartDTO.builder()
                .id(cart.getId())
                .userId(cart.getUser().getId())
                .items(cart.getItems()
                        .stream()
                        .map(this::convertToCartItemDTO)
                        .toList())
                .build();
    }

}
