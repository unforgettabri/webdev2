package com.example.productcatalog.service;

import com.example.productcatalog.entity.Category;
import com.example.productcatalog.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {
        if (categoryRepository.findByNameIgnoreCase(category.getName()).isPresent()) {
            throw new IllegalArgumentException("Category name already exists");
        }
        return categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    public Category updateCategory(Long id, Category updatedCategory) {
        Category existing = getCategoryById(id);

        categoryRepository.findByNameIgnoreCase(updatedCategory.getName())
                .ifPresent(category -> {
                    if (!category.getId().equals(id)) {
                        throw new IllegalArgumentException("Category name already exists");
                    }
                });

        existing.setName(updatedCategory.getName());
        existing.setDescription(updatedCategory.getDescription());

        return categoryRepository.save(existing);
    }

    public void deleteCategory(Long id) {
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
    }
}
