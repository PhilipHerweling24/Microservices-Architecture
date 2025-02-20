package com.a00326153.library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Book {

    @Column(name="book_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title can not be blank")
    @Column(name="book_title")
    private String title;

    @NotBlank(message = "Author can not be blank")
    @Column(name="book_author")
    private String author;

    @NotBlank(message = "ISBN can not be blank")
    @Size(min = 4, max = 20, message = "ISBN has to be between 4 and 20 characters long")
    @Column(name="isbn")
    private String isbn;

    @Column(name="available_copies")
    private int availableCopies;

}
