package com.learn.testing.exception;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class BookError implements Serializable {

    private LocalDateTime localDateTime;
    private int status ;
    private String message;
}
