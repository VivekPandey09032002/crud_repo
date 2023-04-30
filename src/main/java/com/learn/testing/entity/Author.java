package com.learn.testing.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long authorId;

    private String firstName;
    private String lastName;

    private String language;

    @OneToOne(mappedBy = "author")
    private Book book;

}
