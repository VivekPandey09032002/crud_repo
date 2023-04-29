package com.learn.testing.controller;

import com.learn.testing.dto.BookDto;
import com.learn.testing.entity.Book;
import com.learn.testing.exception.CustomBookException;
import com.learn.testing.service.BookService;
import com.learn.testing.utility.BookUtility;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public Book addBook(@RequestBody BookDto bookDto){
        if(BookUtility.isNotValidBook(bookDto)){
            throw new CustomBookException("not a valid book", HttpStatus.BAD_REQUEST);
        }
        return bookService.addBook(BookUtility.bookDtoToBook(bookDto));
    }

    @PostMapping("/all")
    public List<Book> addBooks(@RequestBody List<BookDto> bookDtoList){
        List<Book> books =  bookDtoList.stream()
                .filter(bookDto -> !BookUtility.isNotValidBook(bookDto))
                .map(BookUtility::bookDtoToBook)
                .toList();
        if(books.isEmpty()){
            throw  new CustomBookException("no valid books found in the list",HttpStatus.BAD_REQUEST);
        }

        return bookService.addBooks(books);
    }

    @PutMapping
    public Book updateBook(@RequestBody BookDto bookDto){
        if(BookUtility.isNotValidBook(bookDto)){
            throw new CustomBookException("not a valid book", HttpStatus.BAD_REQUEST);
        }
        return bookService.updateBook(BookUtility.bookDtoToBook(bookDto));
    }

    @DeleteMapping("{bookId}")
    public Book deleteBook(@PathVariable Long bookId){
        return bookService.deleteBook(bookId);
    }

    @GetMapping
    public List<BookDto> getAllBooks(){
        List<Book> books  = bookService.getBooks();
        return BookUtility.booksToBooksDto(books);
    }

    @GetMapping("{bookId}")
    public BookDto getBookById(@PathVariable Long bookId){
        Book book =  bookService.getBookById(bookId);
        return BookUtility.bookToDto(book);
    }


}
