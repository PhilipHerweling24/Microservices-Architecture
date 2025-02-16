package com.a00326153.library.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Book {

    @Column(name="book_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="book_title")
    private String title;

    @Column(name="book_author")
    private String author;

    @Column(name="isbn")
    private String isbn;

    @Column(name="available_copies")
    private int availableCopies;

}
