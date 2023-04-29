package com.learn.testing.service;

import com.learn.testing.entity.Book;

import java.util.List;

public interface BookService {
    Book addBook(Book book);
    List<Book> addBooks(List<Book> books);
    Book getBookById(long id);

    List<Book> getBooks();
    Book updateBook(Book book);

    Book deleteBook(Long bookId);

    List<Book> filterBooks(String name, Double rating);
}
