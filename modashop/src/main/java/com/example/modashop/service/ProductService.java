package com.example.modashop.service;

import com.example.modashop.dto.ProductDTO;
import com.example.modashop.entity.Category;
import com.example.modashop.entity.Product;
import com.example.modashop.exception.EntityNotFoundException;
import com.example.modashop.repository.CategoryRepository;
import com.example.modashop.repository.ProductRepository;
import com.example.modashop.util.EntityHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final EntityHelper entityHelper;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return entityHelper.findOrThrowException(Product.class, id, productRepository);
    }

    // public Product createProduct(Product product) {
    //     return repository.save(product);
    // }

    // public Product updateProduct(Product newProduct, Long id) {
    //     return repository.findById(id)
    //             .map(product -> {
    //                 product.setName(newProduct.getName());
    //                 product.setDescription(newProduct.getDescription());
    //                 product.setPrice(newProduct.getPrice());
    //                 product.setImageUrl(newProduct.getImageUrl());
    //                 product.setDiscount(newProduct.getDiscount());
    //                 product.setCategory(newProduct.getCategory());
    //                 return repository.save(product);
    //             })
    //             .orElseThrow(() -> new EntityNotFoundException("Product", id));
    // }

    public String deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException(Product.class, id);
        }
        productRepository.deleteById(id);
        return "Product with id " + id + " has been deleted";
    }

    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.getProductsByCategoryId(categoryId);
    }

    // DTO
    public Category getCategoryById(Long id){
        return entityHelper.findOrThrowException(Category.class, id, categoryRepository);
    }
    public Product toEntity(ProductDTO dto) {
        Category category = getCategoryById(dto.getCategoryId());
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImageUrl(dto.getImageUrl());
        product.setCategory(category);
        return product;
    }

    public ProductDTO toDTO(Product product){

        return ProductDTO.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .categoryId(product.getCategory().getId())
                .build();
    }

    public Product createProduct(ProductDTO productDTO) {
        return productRepository.save(toEntity(productDTO));
    }

    public Product updateProduct(ProductDTO newProduct, Long id) {
        Product product = entityHelper.findOrThrowException(Product.class, id, productRepository);
        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        product.setCategory(getCategoryById(newProduct.getCategoryId()));
        product.setDescription(newProduct.getDescription());
        if (newProduct.getImageUrl()!= null) product.setImageUrl(newProduct.getImageUrl());
        product.setDiscount(newProduct.getDiscount());
        return productRepository.save(product);
    }



}