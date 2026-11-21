package com.example.productcatalog.dto;

import jakarta.validation.constraints.*;

public class ProductDTO {

    private Long id;

    @NotBlank(message = "Product name is required")
    @Size(max = 150, message = "Product name must not exceed 150 characters")
    private String name;

    @NotBlank(message = "SKU is required")
    @Size(max = 50, message = "SKU must not exceed 50 characters")
    private String sku;

    @NotNull(message = "Price is required")
    @PositiveOrZero(message = "Price must be zero or greater")
    private Double price;

    @NotNull(message = "Stock quantity is required")
    @PositiveOrZero(message = "Stock quantity must be zero or greater")
    private Integer stockQuantity;

    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "Category ID is required")
    @Positive(message = "Category ID must be positive")
    private Long categoryId;

    private String categoryName;

    public ProductDTO() {}

    public ProductDTO(Long id, String name, String sku, Double price,
                     Integer stockQuantity, String status,
                     Long categoryId, String categoryName) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.status = status;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getSku() { return sku; }
    public Double getPrice() { return price; }
    public Integer getStockQuantity() { return stockQuantity; }
    public String getStatus() { return status; }
    public Long getCategoryId() { return categoryId; }
    public String getCategoryName() { return categoryName; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setSku(String sku) { this.sku = sku; }
    public void setPrice(Double price) { this.price = price; }
    public void setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; }
    public void setStatus(String status) { this.status = status; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}
