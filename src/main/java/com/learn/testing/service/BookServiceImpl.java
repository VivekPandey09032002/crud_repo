package com.learn.testing.service;


import com.learn.testing.dto.AuthorDto;
import com.learn.testing.entity.Book;
import com.learn.testing.exception.CustomBookException;
import com.learn.testing.repository.BookRepository;
import com.learn.testing.utility.AuthorUtility;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;


    @Override
    public Book addBook(Book book) {

        if (bookRepository.findById(book.getBookId()).isPresent()) {
            throw new CustomBookException("book already exist in the system", HttpStatus.FOUND);
        }
        AuthorDto authorDto = AuthorUtility.authorToDto(book.getAuthor());
        if(AuthorUtility.isNotValidAuthor( authorDto ) ){
            throw new CustomBookException("cannot add book, invalid author",HttpStatus.BAD_REQUEST);
        }
        return bookRepository.save(book);
    }

    @Override
    public List<Book> addBooks(List<Book> books) {
        List<Book> filteredBooks = books.stream().filter(book -> bookRepository.findById(book.getBookId()).isEmpty()).toList();
        if (filteredBooks.isEmpty()) {
            throw new CustomBookException("no unique book was found", HttpStatus.FOUND);
        }
        for(Book book : filteredBooks){
            AuthorDto authorDto = AuthorUtility.authorToDto(book.getAuthor());
            if (AuthorUtility.isNotValidAuthor(authorDto)) {
                throw new CustomBookException("not a valid author", HttpStatus.BAD_REQUEST);
            }
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
        AuthorDto authorDto = AuthorUtility.authorToDto(book.getAuthor());
        if(AuthorUtility.isNotValidAuthor( authorDto ) ){
            throw new CustomBookException("not a valid author",HttpStatus.BAD_REQUEST);
        }
        dbBook.setAuthor(book.getAuthor());
        return bookRepository.save(dbBook);
    }

    @Override
    public Book deleteBook(Long bookId) {
        Book dbBook = bookRepository.findById(bookId).orElseThrow(() -> new CustomBookException("no book found with given id", HttpStatus.NOT_FOUND));
        bookRepository.delete(dbBook);
        return dbBook;
    }

    @Override
    public List<Book> filterBooks(String name, Double rating) {
        Set<Book> bookSet =  bookRepository.findByNameContainsIgnoreCase(name);
        Set<Book> bookSet1 = bookRepository.findByRatingGreaterThanEqual(rating);
        bookSet.retainAll(bookSet1);
        return new ArrayList<>(bookSet);
    }
}
