package com.learn.testing.controller;


import com.learn.testing.exception.BookError;
import com.learn.testing.exception.CustomBookException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BookControllerAdvice {

    @ExceptionHandler(CustomBookException.class)
    public ResponseEntity<BookError>  bookError(CustomBookException exception){
        return  ResponseEntity.status(exception.getError().getStatus()).body(exception.getError());
    }
}
