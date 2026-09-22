package com.example.webdev2;

import com.example.webdev2.model.Book;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final List<Book> books = new ArrayList<>();

    public BookController() {
        books.add(new Book(1L, "The Little Prince", "Antoine de Saint-Exupéry"));
        books.add(new Book(2L, "Harry Potter", "J.K. Rowling"));
        books.add(new Book(3L, "The Hobbit", "J.R.R. Tolkien"));
    }

    @GetMapping
    public List<Book> getAllBooks(
            @RequestParam(required = false) String author) {

        if (author == null) {
            return books;
        }

        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .toList();
    }
}
