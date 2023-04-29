package com.learn.testing.service;

import com.learn.testing.entity.Author;
import com.learn.testing.entity.Book;
import com.learn.testing.exception.CustomBookException;
import com.learn.testing.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final BookRepository bookRepository;

    @Override
    public Author getAuthorById(long bookId) {
        return bookRepository
                .findById(bookId)
                .orElseThrow(() -> new CustomBookException("no author found with such id", HttpStatus.NOT_FOUND))
                .getAuthor();
    }

    @Override
    public Author updateAuthor(long bookId, Author author) {
        Book dbBook = bookRepository
                .findById(bookId)
                .orElseThrow(() -> new CustomBookException("no book found with given id", HttpStatus.NOT_FOUND));
        dbBook.setAuthor(author);
        bookRepository.save(dbBook);
        return author;
    }

    @Override
    public Author deleteAuthor(long bookId) {
        Book dbBook = bookRepository
                .findById(bookId)
                .orElseThrow(() -> new CustomBookException("no book found with given id", HttpStatus.NOT_FOUND));
        Author author = dbBook.getAuthor();
        dbBook.setAuthor(null);
        bookRepository.save(dbBook);
        return author;
    }
}
