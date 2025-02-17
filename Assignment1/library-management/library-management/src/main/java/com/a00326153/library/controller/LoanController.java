package com.a00326153.library.controller;

import com.a00326153.library.dto.LoanDto;
import com.a00326153.library.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService){
        this.loanService = loanService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<LoanDto>> getLoansByUser(@PathVariable Long userId, Pageable pageable){
        return ResponseEntity.ok(loanService.getLoansByUser(userId, pageable));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<Page<LoanDto>> getLoansByBook(@PathVariable Long bookId, Pageable pageable){
        return ResponseEntity.ok(loanService.getLoansByBook(bookId, pageable));
    }

    @PostMapping("/{userId}/{bookId}")
    public ResponseEntity<LoanDto> createLoan(@PathVariable Long userId, @PathVariable Long bookId){
        return ResponseEntity.ok(loanService.createLoan(userId, bookId));
    }

    @GetMapping
    public List<LoanDto> getAllLoans(){
        return loanService.getAllLoans();
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<List<LoanDto>> getLoanById(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.getLoanById(id));
    }

    @PutMapping("/return/{loanId}")
    public ResponseEntity<LoanDto> returnLoan(@PathVariable Long loanId){
        return ResponseEntity.ok(loanService.returnBook(loanId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id){
        loanService.deleteLoan(id);
        return ResponseEntity.noContent().build();
    }

}
