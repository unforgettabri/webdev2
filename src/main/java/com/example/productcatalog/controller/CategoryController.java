package com.example.productcatalog.controller;

import com.example.productcatalog.dto.CategoryDTO;
import com.example.productcatalog.entity.Category;
import com.example.productcatalog.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO createCategory(@Valid @RequestBody CategoryDTO dto) {
        Category category = new Category(dto.getName(), dto.getDescription());
        Category saved = categoryService.createCategory(category);
        return toDTO(saved);
    }

    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories().stream()
                .map(this::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id) {
        return toDTO(categoryService.getCategoryById(id));
    }

    @PutMapping("/{id}")
    public CategoryDTO updateCategory(@PathVariable Long id,
                                     @Valid @RequestBody CategoryDTO dto) {
        Category updated = new Category(dto.getName(), dto.getDescription());
        return toDTO(categoryService.updateCategory(id, updated));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

    private CategoryDTO toDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getName(),
                category.getDescription());
    }
}
