package com.example.productcatalog.controller;

import com.example.productcatalog.dto.ProductDTO;
import com.example.productcatalog.entity.Category;
import com.example.productcatalog.entity.Product;
import com.example.productcatalog.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProduct(@Valid @RequestBody ProductDTO dto) {
        Product saved = productService.createProduct(toEntity(dto));
        return toDTO(saved);
    }

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts().stream()
                .map(this::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        return toDTO(productService.getProductById(id));
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id,
                                    @Valid @RequestBody ProductDTO dto) {
        return toDTO(productService.updateProduct(id, toEntity(dto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/status/{status}")
    public List<ProductDTO> getProductsByStatus(@PathVariable String status) {
        return productService.getProductsByStatus(status).stream()
                .map(this::toDTO)
                .toList();
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductDTO> getProductsByCategory(@PathVariable Long categoryId) {
        return productService.getProductsByCategory(categoryId).stream()
                .map(this::toDTO)
                .toList();
    }

    @GetMapping("/search")
    public Page<ProductDTO> searchProducts(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Pageable pageable = createPageable(page, size, sortBy, direction);
        return productService.searchProducts(keyword, pageable).map(this::toDTO);
    }

    @GetMapping("/price-range")
    public Page<ProductDTO> getProductsByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "price") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Pageable pageable = createPageable(page, size, sortBy, direction);
        return productService.getProductsByPriceRange(minPrice, maxPrice, pageable)
                .map(this::toDTO);
    }

    private Pageable createPageable(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        return PageRequest.of(page, size, sort);
    }

    private Product toEntity(ProductDTO dto) {
        Category category = new Category();
        category.setId(dto.getCategoryId());

        Product product = new Product();
        product.setName(dto.getName());
        product.setSku(dto.getSku());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setStatus(dto.getStatus());
        product.setCategory(category);

        return product;
    }

    private ProductDTO toDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getSku(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getStatus(),
                product.getCategory().getId(),
                product.getCategory().getName()
        );
    }
}
