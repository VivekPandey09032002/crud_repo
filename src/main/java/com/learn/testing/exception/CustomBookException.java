package com.learn.testing.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class CustomBookException extends RuntimeException  {

    private final transient BookError error;

    public CustomBookException(String message, HttpStatus httpStatus) {
        super(message);
        error =  BookError.builder().message(message).status(httpStatus.value()).localDateTime(LocalDateTime.now()).build();
    }
}
