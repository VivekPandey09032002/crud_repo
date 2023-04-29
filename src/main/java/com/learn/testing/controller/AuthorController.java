package com.learn.testing.controller;

import com.learn.testing.dto.AuthorDto;
import com.learn.testing.entity.Author;
import com.learn.testing.exception.CustomBookException;
import com.learn.testing.service.AuthorService;
import com.learn.testing.utility.AuthorUtility;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
@AllArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("{bookId}")
    public AuthorDto getAuthor(@PathVariable Long bookId){
        return AuthorUtility.authorToDto(authorService.getAuthorById(bookId));
    }

    @PutMapping("{bookId}")
    public AuthorDto updateAuthor(@PathVariable Long bookId, @RequestBody AuthorDto authorDto){
        if(AuthorUtility.isNotValidAuthor(authorDto)){
            throw new CustomBookException("not a valid author", HttpStatus.BAD_REQUEST);
        }
        return AuthorUtility.authorToDto(authorService.updateAuthor(bookId,AuthorUtility.dtoToAuthor(authorDto)));
    }

    @DeleteMapping("{bookId}")
    public AuthorDto deleteAuthor(@PathVariable Long bookId){
        return AuthorUtility.authorToDto(authorService.deleteAuthor(bookId)) ;
    }

}
