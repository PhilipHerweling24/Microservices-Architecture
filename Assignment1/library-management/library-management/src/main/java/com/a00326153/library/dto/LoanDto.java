package com.a00326153.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class LoanDto {
    private Long loanId;
    private Long bookId;
    private Long userId;
    private LocalDate returnDate;

}

