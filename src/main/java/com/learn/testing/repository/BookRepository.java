package com.learn.testing.repository;

import com.learn.testing.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    @Query("select b from Book b where b.rating >= ?1")
    Set<Book> findByRatingGreaterThanEqual(double rating);
    @Query("select b from Book b where upper(b.name) like upper(concat('%', ?1, '%'))")
    Set<Book> findByNameContainsIgnoreCase(String name);


}
