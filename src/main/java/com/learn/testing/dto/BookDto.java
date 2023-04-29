package com.learn.testing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("authorInfo")
    private AuthorDto authorDto;
}
