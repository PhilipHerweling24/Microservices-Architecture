package com.a00326153.library.service;

import com.a00326153.library.dto.LoanDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface LoanService {

    List<LoanDto> getAllLoans();

    List<LoanDto> getLoanById(Long id);

    Page<LoanDto> getLoansByUser(Long userId, Pageable pageable);

    Page<LoanDto> getLoansByBook(Long bookId, Pageable pageable);

    LoanDto createLoan(Long userId, Long bookId);

    LoanDto returnBook(Long loanId);

    void deleteLoan(Long id);

    Page<LoanDto> getMostRecentLoans(Pageable pageable);

    Page<LoanDto> getLoansByDueDate(LocalDate dueDate, Pageable pageable);
}
