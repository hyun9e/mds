package com.example.modashop.service;

import com.example.modashop.entity.Category;
import com.example.modashop.exception.EntityNotFoundException;
import com.example.modashop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    public Category getCategoryById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category", id));
    }

    public Category createCategory(Category category) {
        return repository.save(category);
    }

    public Category updateCategory(Category newCategory, Long id) {
        return repository.findById(id)
                .map(category -> {
                    category.setName(newCategory.getName());
                    return repository.save(category);
                })
                .orElseThrow(() -> new EntityNotFoundException("Category", id));
    }

    public String deleteCategory(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Category", id);
        }
        repository.deleteById(id);
        return "Category with id " + id + " has been deleted";
    }
}