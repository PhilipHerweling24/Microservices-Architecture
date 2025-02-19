package com.a00326153.library.controller;

import com.a00326153.library.constants.ControllerConstants;
import com.a00326153.library.dto.LoanDto;
import com.a00326153.library.dto.ResponseDto;
import com.a00326153.library.serviceImpl.LoanServiceImpl;
import jakarta.validation.Valid;
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

    private final LoanServiceImpl loanService;

    @Autowired
    public LoanController(LoanServiceImpl loanService){
        this.loanService = loanService;
    }

    //Gets Loans by User ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<LoanDto>> getLoansByUser(@Valid@PathVariable Long userId, Pageable pageable){
        return ResponseEntity.ok(loanService.getLoansByUser(userId, pageable));
    }

    //Gets Loans by Book ID
    @GetMapping("/book/{bookId}")
    public ResponseEntity<Page<LoanDto>> getLoansByBook(@Valid@PathVariable Long bookId, Pageable pageable){
        return ResponseEntity.ok(loanService.getLoansByBook(bookId, pageable));
    }

    //Creates a Loan Using a Book ID and User ID
    @PostMapping("/{userId}/{bookId}")
    public ResponseEntity<ResponseDto> createLoan(@Valid@PathVariable Long userId, @Valid@PathVariable Long bookId){
        loanService.createLoan(userId, bookId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(ControllerConstants.STATUS_201, ControllerConstants.MESSAGE_201));
    }

    //Gets all Loans
    @GetMapping
    public ResponseEntity<List<LoanDto>> getAllLoans(){
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    //Gets Loans by Loan ID
    @GetMapping("/byId/{id}")
    public ResponseEntity<List<LoanDto>> getLoanById(@Valid@PathVariable Long id) {
        return ResponseEntity.ok(loanService.getLoanById(id));
    }

    //Returns Book on Loan using the Loan ID
    @PutMapping("/return/{loanId}")
    public ResponseEntity<ResponseDto> returnLoan(@Valid@PathVariable Long loanId){
        loanService.returnBook(loanId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(ControllerConstants.STATUS_201, ControllerConstants.MESSAGE_201_RETURN));
    }

    //Deletes a Loan by using the Loan ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteLoan(@Valid@PathVariable Long id){
        loanService.deleteLoan(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(ControllerConstants.STATUS_201, ControllerConstants.MESSAGE_201_DELETE));
    }

    //Returns the most recent Loans
    @GetMapping("/recent")
    public ResponseEntity<Page<LoanDto>> getRecentLoans(Pageable pageable) {
        return ResponseEntity.ok(loanService.getMostRecentLoans(pageable));
    }

    //Returns the Loans which due dates are close
    @GetMapping("/due-date")
    public ResponseEntity<Page<LoanDto>> getLoansByDueDate(@Valid @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate dueDate, Pageable pageable){
        return ResponseEntity.ok(loanService.getLoansByDueDate(dueDate, pageable));
    }
}
