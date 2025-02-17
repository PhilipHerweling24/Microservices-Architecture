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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public List<LoanDto> getAllLoans() {
        return loanRepository.findAll().stream().map(loan -> LoanMapper.mapToLoanDto(loan)).toList();
    }

    public List<LoanDto> getLoanById(Long id) {
        return loanRepository.findById(id).stream().map(loan -> LoanMapper.mapToLoanDto(loan)).toList();
    }

    public Page<LoanDto> getLoansByUser(Long id, Pageable pageable){
        //userRepository.findById(id);
        return loanRepository.findByUserId(id, pageable).map(loan -> LoanMapper.mapToLoanDto(loan));
    }

    public Page<LoanDto> getLoansByBook(Long id, Pageable pageable){
        return loanRepository.findByBookId(id, pageable).map(loan -> LoanMapper.mapToLoanDto(loan));
    }

    public LoanDto createLoan(Long userId, Long bookId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found, Ensure user has been created"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found, Ensure book exists"));

        //TODO add if statement which checks avialable copies
        if (book.getAvailableCopies() <=0){
            throw new RuntimeException("No Available Copies left at his time.");
        }
        //TODO lower availability count if book is available
        book.setAvailableCopies(book.getAvailableCopies()-1);
        bookRepository.save(book);

        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setBorrowedDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(21));
        loan.setReturned(false);

        return LoanMapper.mapToLoanDto(loanRepository.save(loan));
    }

    public LoanDto returnBook(Long loanId){
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Loan not found with ID: "+loanId));

        if (loan.isReturned()){
            throw new RuntimeException("This book has already been returned!");
        }

        loan.setReturned(true);

        Book book = loan.getBook();
        book.setAvailableCopies(book.getAvailableCopies()+1);
        bookRepository.save(book);
        return LoanMapper.mapToLoanDto(loanRepository.save(loan));
    }


    public void deleteLoan(Long id){
        loanRepository.deleteById(id);
    }





}
