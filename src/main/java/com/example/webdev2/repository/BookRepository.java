package com.example.webdev2.repository;

import com.example.webdev2.model.Category;
import com.example.webdev2.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    private final List<Book> books = new ArrayList<>();

    public BookRepository() {
        Book hobbit = new Book(1L, "The Hobbit", "J.R.R. Tolkien", new Category("Fantasy"));
        hobbit.setPrice(450.0);
        books.add(hobbit);

        Book book1984 = new Book(2L, "1984", "George Orwell", new Category("Classic"));
        book1984.setPrice(350.0);
        books.add(book1984);
    }

    public List<Book> findAll() {
        return books;
    }

    public Book findById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Book save(Book book) {
        if (book.getId() == null) {
            long nextId = books.stream()
                    .mapToLong(Book::getId)
                    .max()
                    .orElse(0) + 1;
            book.setId(nextId);
        }

        books.add(book);
        return book;
    }

    public void update(Book book) {
        Book existing = findById(book.getId());

        if (existing != null) {
            existing.setTitle(book.getTitle());
            existing.setAuthor(book.getAuthor());
            existing.setCategory(book.getCategory());
            existing.setPrice(book.getPrice());
        }
    }

    public void delete(Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }
}