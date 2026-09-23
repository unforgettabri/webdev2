package com.example.webdev2.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Book {

    private Long id;

    @NotBlank(message = "Title cannot be blank.")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters.")
    private String title;

    @NotBlank(message = "Author cannot be blank.")
    @Size(min = 2, max = 100, message = "Author must be between 2 and 100 characters.")
    private String author;

    @NotNull(message = "Category is required.")
    private Category category;

    @NotNull(message = "Price is required.")
    @Min(value = 1, message = "Price must be at least 1.")
    private Double price;

    public Book() {
    }

    public Book(Long id, String title, String author, Category category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}