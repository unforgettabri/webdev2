package com.example.webdev2.service;

import com.example.webdev2.model.Book;
import com.example.webdev2.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    public Book findById(Long id) {
        return repository.findById(id);
    }

    public Book save(Book book) {
        return repository.save(book);
    }

    public void update(Book book) {
        repository.update(book);
    }

    public void delete(Long id) {
        repository.delete(id);
    }
}