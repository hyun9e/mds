package com.example.modashop.service;

import com.example.modashop.entity.Product;
import com.example.modashop.exception.EntityNotFoundException;
import com.example.modashop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product", id));
    }

    public Product createProduct(Product product) {
        return repository.save(product);
    }

    public Product updateProduct(Product newProduct, Long id) {
        return repository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setDescription(newProduct.getDescription());
                    product.setPrice(newProduct.getPrice());
                    product.setImageUrl(newProduct.getImageUrl());
                    product.setDiscount(newProduct.getDiscount());
                    product.setCategory(newProduct.getCategory());
                    return repository.save(product);
                })
                .orElseThrow(() -> new EntityNotFoundException("Product", id));
    }

    public String deleteProduct(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Product", id);
        }
        repository.deleteById(id);
        return "Product with id " + id + " has been deleted";
    }

    public List<Product> getProductsByCategory(Long categoryId) {
        return repository.getProductsByCategoryId(categoryId);
    }
}