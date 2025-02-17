package com.a00326153.library.repository;

import com.a00326153.library.entity.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    Page<Loan> findByUserId(Long userId, Pageable pageable);
    Page<Loan> findByBookId(Long bookId, Pageable pageable);
    Page<Loan> findTop10ByOrderByBorrowedDateDesc(Pageable pageable);
    Page<Loan> findByDueDate(LocalDate dueDate, Pageable pageable);
}
