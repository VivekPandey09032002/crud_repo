package com.learn.testing.service;

import com.learn.testing.entity.Author;

public interface AuthorService {
    Author getAuthorById(long bookId);
    Author updateAuthor(long bookId, Author author);
    Author deleteAuthor(long bookId);
}
