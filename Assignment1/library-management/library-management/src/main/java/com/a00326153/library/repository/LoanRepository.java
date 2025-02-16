package com.a00326153.library.repository;

import com.a00326153.library.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUserId(Long userId);
    List<Loan> findByBookId(Long bookId);
    List<Loan> findByReturnedFalse();
    List<Loan> findByBorrowedDateBetween(LocalDate startDate, LocalDate endDate);
}
