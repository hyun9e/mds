package com.example.modashop.service;

import com.example.modashop.entity.ProductVariant;
import com.example.modashop.exception.EntityNotFoundException;
import com.example.modashop.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductVariantService {
    private final ProductVariantRepository repository;

    public List<ProductVariant> getAllVariants() {
        return repository.findAll();
    }

    public ProductVariant getVariantById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product Variant", id));
    }

    public List<ProductVariant> getVariantsByProduct(Long productId) {
        return repository.findByProductId(productId);
    }

    public ProductVariant createVariant(ProductVariant variant) {
        return repository.save(variant);
    }

    public ProductVariant updateVariant(ProductVariant newVariant, Long id) {
        return repository.findById(id)
                .map(variant -> {
                    variant.setSize(newVariant.getSize());
                    variant.setColor(newVariant.getColor());
                    variant.setStock(newVariant.getStock());
                    variant.setProduct(newVariant.getProduct());
                    return repository.save(variant);
                })
                .orElseThrow(() -> new EntityNotFoundException("Product Variant", id));
    }

    public String deleteVariant(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Product Variant", id);
        }
        repository.deleteById(id);
        return "Product variant with id " + id + " has been deleted";
    }
}