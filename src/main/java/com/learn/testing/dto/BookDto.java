package com.learn.testing.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {


    private Long bookId;

    private String name;

    private String summary;

    private double rating;
}
