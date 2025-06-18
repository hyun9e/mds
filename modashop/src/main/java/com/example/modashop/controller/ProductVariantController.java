package com.example.modashop.controller;

import com.example.modashop.entity.ProductVariant;
import com.example.modashop.service.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
@RequestMapping("/variants")
public class ProductVariantController {
    private final ProductVariantService service;

    @GetMapping
    public List<ProductVariant> getAllVariants() {
        return service.getAllVariants();
    }

    @GetMapping("/{id}")
    public ProductVariant getVariantById(@PathVariable Long id) {
        return service.getVariantById(id);
    }

    @GetMapping("/product/{productId}")
    public List<ProductVariant> getVariantsByProduct(@PathVariable Long productId) {
        return service.getVariantsByProduct(productId);
    }

    @PostMapping
    public ProductVariant createVariant(@RequestBody ProductVariant variant) {
        return service.createVariant(variant);
    }

    @PutMapping("/{id}")
    public ProductVariant updateVariant(@RequestBody ProductVariant variant, @PathVariable Long id) {
        return service.updateVariant(variant, id);
    }

    @DeleteMapping("/{id}")
    public String deleteVariant(@PathVariable Long id) {
        return service.deleteVariant(id);
    }
}