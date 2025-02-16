package com.a00326153.library.service;

import com.a00326153.library.dto.LoanDto;
import com.a00326153.library.entity.Book;
import com.a00326153.library.entity.Loan;
import com.a00326153.library.entity.User;
import com.a00326153.library.mapper.LoanMapper;
import com.a00326153.library.repository.BookRepository;
import com.a00326153.library.repository.LoanRepository;
import com.a00326153.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository){
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan getLoanById(Long id) {
        return loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Loan with not found with id: "+id));
    }

    public List<LoanDto> getLoansByUser(Long id){
        //userRepository.findById(id);
        return loanRepository.findByUserId(id).stream().map(loan -> LoanMapper.mapToLoanDto(loan)).toList();
    }

    public LoanDto createLoan(Long userId, Long bookId){
        User user = userRepository.findById(userId).orElseThrow();
        Book book = bookRepository.findById(bookId).orElseThrow();

        //TODO add if statement which checks avialable copies
        //TODO lower availability count if book is available

        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setBorrowedDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(21));
        loan.setReturned(false);

        return LoanMapper.mapToLoanDto(loanRepository.save(loan));
    }



}
