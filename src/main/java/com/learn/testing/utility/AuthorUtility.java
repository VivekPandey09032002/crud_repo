package com.learn.testing.utility;

import com.learn.testing.dto.AuthorDto;
import com.learn.testing.dto.BookDto;
import com.learn.testing.entity.Author;
import com.learn.testing.entity.Book;

import java.util.List;

public class AuthorUtility {

    private AuthorUtility(){}

    public static AuthorDto authorToDto(Author author) {
        if(author == null){
            return null;
        }
        return AuthorDto.builder()
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .language(author.getLanguage())
                .authorId(author.getAuthorId())
                .build();
    }

    public static Author dtoToAuthor(AuthorDto authorDto){
        return Author.builder()
                .firstName(authorDto.getFirstName())
                .lastName(authorDto.getLastName())
                .language(authorDto.getLanguage())
                .build();
    }

    public static boolean isNotValidAuthor(AuthorDto authorDto){
        return authorDto == null ||  authorDto.getFirstName() == null || authorDto.getLastName() == null || authorDto.getLanguage() == null;
    }

}
