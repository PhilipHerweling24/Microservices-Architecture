package com.a00326153.library.controller;

import com.a00326153.library.constants.LoanConstants;
import com.a00326153.library.dto.LoanDto;
import com.a00326153.library.dto.ResponseDto;
import com.a00326153.library.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public ResponseEntity<ResponseDto> createLoan(@PathVariable Long userId, @PathVariable Long bookId){
        loanService.createLoan(userId, bookId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(LoanConstants.STATUS_201,LoanConstants.MESSAGE_201));
    }

    @GetMapping
    public ResponseEntity<List<LoanDto>> getAllLoans(){
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<List<LoanDto>> getLoanById(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.getLoanById(id));
    }

    @PutMapping("/return/{loanId}")
    public ResponseEntity<ResponseDto> returnLoan(@PathVariable Long loanId){
        loanService.returnBook(loanId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(LoanConstants.STATUS_201, LoanConstants.MESSAGE_201_RETURN));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteLoan(@PathVariable Long id){
        loanService.deleteLoan(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(LoanConstants.STATUS_201, LoanConstants.MESSAGE_201_DELETE));
    }

    @GetMapping("/recent")
    public ResponseEntity<Page<LoanDto>> getRecentLoans(Pageable pageable) {
        return ResponseEntity.ok(loanService.getMostRecentLoans(pageable));
    }

    @GetMapping("/due-date")
    public ResponseEntity<Page<LoanDto>> getLoansByDueDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate dueDate, Pageable pageable){
        return ResponseEntity.ok(loanService.getLoansByDueDate(dueDate, pageable));
    }
}
