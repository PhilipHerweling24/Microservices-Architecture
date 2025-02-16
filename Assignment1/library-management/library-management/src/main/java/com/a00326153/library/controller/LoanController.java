package com.a00326153.library.controller;

import com.a00326153.library.dto.LoanDto;
import com.a00326153.library.entity.Loan;
import com.a00326153.library.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{userId}")
    public ResponseEntity<List<LoanDto>> getLoansByUser(@PathVariable Long userId){
        return ResponseEntity.ok(loanService.getLoansByUser(userId));
    }

    @PostMapping("/{userId}/{bookId}")
    public ResponseEntity<LoanDto> createLoan(@PathVariable Long userId, @PathVariable Long bookId){
        return ResponseEntity.ok(loanService.createLoan(userId, bookId));
    }

}
