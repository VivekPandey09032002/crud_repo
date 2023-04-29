package com.learn.testing.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book_records")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Book {
    @Id
    private Long bookId;

    @NonNull
    private String name;

    @NonNull
    private String summary;

    private double rating;
}
