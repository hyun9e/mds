package com.example.modashop.service;

import com.example.modashop.entity.Category;
import com.example.modashop.exception.EntityNotFoundException;
import com.example.modashop.repository.CategoryRepository;
import com.example.modashop.util.EntityHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final EntityHelper entityHelper;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return entityHelper.findOrThrowException(Category.class, id, categoryRepository);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category newCategory, Long id) {
        Category category = entityHelper.findOrThrowException(Category.class, id, categoryRepository);
        category.setName(newCategory.getName());
        return categoryRepository.save(category);
    }

    public String deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException(Category.class, id);
        }
        categoryRepository.deleteById(id);
        return "Category with id " + id + " has been deleted";
    }
}