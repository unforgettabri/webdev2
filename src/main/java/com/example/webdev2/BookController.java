package com.example.webdev2;

import com.example.webdev2.model.Category;
import com.example.webdev2.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final List<Book> books = new ArrayList<>();

    public BookController() {
        books.add(new Book(1L, "The Little Prince", "Antoine de Saint-Exupéry", new Category("Classic")));
        books.add(new Book(2L, "Harry Potter", "J.K. Rowling", new Category("Fantasy")));
        books.add(new Book(3L, "The Hobbit", "J.R.R. Tolkien", new Category("Fantasy")));
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
    public String getBookById(@PathVariable Long id, Model model) {

        Book book = books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (book == null) {
            return "redirect:/books/list";
        }

        model.addAttribute("book", book);
        return "book-detail";
    }

    @GetMapping("/list")
    public String bookList(Model model) {
        model.addAttribute("books", books);
        return "book-list";
    }

    @GetMapping("/new")
    public String showBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "book-form";
    }

    @PostMapping
    public String createBook(@ModelAttribute Book book) {
        book.setId((long) (books.size() + 1));
        books.add(book);
        return "redirect:/books/list";
    }
}