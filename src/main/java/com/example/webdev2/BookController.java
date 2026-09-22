package com.example.webdev2;

import com.example.webdev2.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Controller
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

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {

        Book book = books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(book);
    }

    @GetMapping("/list")
        public String bookList(Model model) {
            List<Book> books = new ArrayList<>();

            books.add(new Book(1L, "The Little Prince", "Antoine de Saint-Exupéry"));
            books.add(new Book(2L, "Harry Potter", "J.K. Rowling"));

            model.addAttribute("books", books);

            return "book-list";
        }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        books.add(book);
        return book;
    }
}