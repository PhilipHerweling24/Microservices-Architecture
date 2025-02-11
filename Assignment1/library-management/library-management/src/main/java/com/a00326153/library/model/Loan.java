package com.a00326153.library.model;

import java.time.LocalDate;

public class Loan {

    private Long id;
    private Book book;
    private LocalDate borrowedDate;
    private LocalDate dueDate;

    private boolean returned;
}
