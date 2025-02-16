package com.a00326153.library.mapper;

import com.a00326153.library.dto.LoanDto;
import com.a00326153.library.entity.Loan;

public class LoanMapper {

    public static LoanDto mapToLoanDto (Loan loan){
        LoanDto loanDto = new LoanDto();
        loanDto.setLoanId(loan.getId());
        loanDto.setBookId(loan.getBook().getId());
        loanDto.setUserId(loan.getUser().getId());
        loanDto.setReturnDate(loan.getDueDate());

        return loanDto;
    }

}
