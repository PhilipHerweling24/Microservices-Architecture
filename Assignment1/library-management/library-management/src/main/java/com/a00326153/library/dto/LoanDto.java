package com.a00326153.library.dto;

import com.a00326153.library.controller.LoanController;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class LoanDto extends RepresentationModel<LoanDto> {
    private Long loanId;

    @NotNull(message = "Book ID is required")
    private Long bookId;
    @NotNull(message = "User ID is required")
    private Long userId;
    @NotNull(message = "User Name is required")
    private String userName;
    @NotNull(message = "Title is required")
    private String title;

    @NotNull(message = "Return date is required")
    @FutureOrPresent(message = "Due date cannot be in the past")
    private LocalDate returnDate;


    public void addHateoasLinks() {

        this.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LoanController.class).getLoanById(this.loanId)).withSelfRel());
        this.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LoanController.class).getAllLoans()).withRel("all-loans"));
        this.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LoanController.class).getLoansByUser(this.userId, null)).withRel("user-loans"));
        this.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LoanController.class).getLoansByBook(this.bookId, null)).withRel("book-loans"));
        this.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LoanController.class).deleteLoan(this.loanId)).withRel("delete-loan"));
    }

}

