package com.example.webdev2;

import com.example.webdev2.model.Book;
import com.example.webdev2.model.Category;
import com.example.webdev2.service.BookService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/list")
    public String bookList(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "book-list";
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);

        if (book == null) {
            return "redirect:/books/list";
        }

        model.addAttribute("book", book);
        return "book-detail";
    }

    @GetMapping("/new")
    public String showBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "book-form";
    }

    @PostMapping
    public String createBook(
            @Valid @ModelAttribute("book") Book book,
            BindingResult result) {

        if (result.hasErrors()) {
            return "book-form";
        }

        bookService.save(book);
        return "redirect:/books/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);

        if (book == null) {
            return "redirect:/books/list";
        }

        model.addAttribute("book", book);
        return "book-form";
    }

    @PostMapping("/update/{id}")
    public String updateBook(
            @PathVariable Long id,
            @Valid @ModelAttribute("book") Book book,
            BindingResult result) {

        if (result.hasErrors()) {
            book.setId(id);
            return "book-form";
        }

        book.setId(id);
        bookService.update(book);
        return "redirect:/books/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books/list";
    }

    @ModelAttribute("categories")
    public Category[] categories() {
        return new Category[] {
                new Category("Classic"),
                new Category("Fantasy"),
                new Category("Science Fiction"),
                new Category("Romance")
        };
    }
}