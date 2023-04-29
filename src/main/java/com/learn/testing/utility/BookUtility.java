package com.learn.testing.utility;

import com.learn.testing.dto.BookDto;
import com.learn.testing.entity.Book;

import java.util.List;

public class BookUtility {

    private BookUtility(){}

    public static BookDto bookToDto(Book book) {
        return BookDto.builder()
                .bookId(book.getBookId())
                .name(book.getName())
                .summary(book.getSummary())
                .rating(book.getRating())
                .build();
    }

    public static Book bookDtoToBook(BookDto bookDto){
        return Book.builder()
                .bookId(bookDto.getBookId())
                .name(bookDto.getName())
                .summary(bookDto.getSummary())
                .rating(bookDto.getRating())
                .build();
    }
    public static List<BookDto> booksToBooksDto(List<Book> books){

        return books.stream().map(BookUtility::bookToDto).toList();
    }

    public static boolean isNotValidBook(BookDto bookDto){
        return bookDto == null || bookDto.getBookId() == null || bookDto.getName() == null || bookDto.getSummary() == null;
    }

}
