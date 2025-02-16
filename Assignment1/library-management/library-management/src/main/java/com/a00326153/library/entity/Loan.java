package com.a00326153.library.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Loan {

    @Column(name="loan_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name="borrowed_date")
    private LocalDate borrowedDate;

    @Column(name="due_date")
    private LocalDate dueDate;

    @Column(name="returned")
    private boolean returned;
}
