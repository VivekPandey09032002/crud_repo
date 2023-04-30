package com.learn.testing.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learn.testing.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDto {
    @JsonIgnore
    private long authorId;

    private String firstName;
    private String lastName;

    private String language;

}
