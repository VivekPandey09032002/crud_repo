package com.learn.testing.service;


import com.learn.testing.entity.Book;
import com.learn.testing.exception.CustomBookException;
import com.learn.testing.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;


    @Override
    public Book addBook(Book book) {
        if (bookRepository.findById(book.getBookId()).isPresent()) {
            throw new CustomBookException("book already exist in the system", HttpStatus.FOUND);
        }
        return bookRepository.save(book);
    }

    @Override
    public List<Book> addBooks(List<Book> books) {
        List<Book> filteredBooks = books.stream().filter(book -> bookRepository.findById(book.getBookId()).isEmpty()).toList();
        if (filteredBooks.isEmpty()) {
            throw new CustomBookException("no unique book was found", HttpStatus.FOUND);
        }

        return bookRepository.saveAll(filteredBooks);
    }

    @Override
    public Book getBookById(long id) {
        return bookRepository.findById(id).orElseThrow(() -> new CustomBookException("no book found with such id", HttpStatus.NOT_FOUND));
    }


    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book updateBook(Book book) {
        Book dbBook = bookRepository.findById(book.getBookId()).orElseThrow(() -> new CustomBookException("no book found with given id", HttpStatus.NOT_FOUND));
        dbBook.setName(book.getName());
        dbBook.setSummary(book.getSummary());
        dbBook.setRating(book.getRating());
        return bookRepository.save(dbBook);
    }

    @Override
    public Book deleteBook(Long bookId) {
        Book dbBook = bookRepository.findById(bookId).orElseThrow(() -> new CustomBookException("no book found with given id", HttpStatus.NOT_FOUND));
        bookRepository.delete(dbBook);
        return dbBook;
    }


}
