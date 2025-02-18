package com.a00326153.library.dto;

import com.a00326153.library.controller.BookController;
import com.a00326153.library.controller.LoanController;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class BookDto extends RepresentationModel<BookDto> {
    private Long bookId;
    @NotBlank(message = "Title can not be blank")
    private String title;

    @NotBlank(message = "Author can not be blank")
    private String author;

    @NotBlank(message = "ISBN can not be blank")
    @Size(min = 4, max = 20, message = "ISBN has to be between 4 and 20 characters long")
    private String isbn;

    public void addHateoasLinks() {
        this.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getAllBooks(null)).withRel("all-books"));
        this.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(LoanController.class).getLoansByBook(this.bookId, null)).withRel("book-loans"));
        this.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).deleteBook(this.bookId)).withRel("delete-book"));
    }
}
