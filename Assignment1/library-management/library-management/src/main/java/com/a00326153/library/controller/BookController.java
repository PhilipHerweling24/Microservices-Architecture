package com.a00326153.library.controller;

import com.a00326153.library.constants.ControllerConstants;
import com.a00326153.library.dto.BookDto;
import com.a00326153.library.dto.ResponseDto;
import com.a00326153.library.entity.Book;
import com.a00326153.library.serviceImpl.BookServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookServiceImpl bookService;

    @Autowired
    public BookController(BookServiceImpl bookService){
        this.bookService = bookService;
    }

    //Creates a Book
    @PostMapping
    public ResponseEntity<ResponseDto> createBook(@Valid @RequestBody Book book) {
        bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(ControllerConstants.STATUS_201, ControllerConstants.MESSAGE_201_BOOK_CREATE));
    }

    //Updates a Book, using a Book ID
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto> updateBook(@Valid@PathVariable Long id, @Valid@RequestBody Book book) {
        bookService.updateBook(id, book);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(ControllerConstants.STATUS_201, ControllerConstants.MESSAGE_201_BOOK_UPDATE));
    }

    //Deletes a Book, using a Book ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteBook(@Valid@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(ControllerConstants.STATUS_201, ControllerConstants.MESSAGE_201_BOOK_DELETE));
    }

    //Gets all Books
    @GetMapping
    public ResponseEntity<Page<BookDto>> getAllBooks(Pageable pageable){
        return ResponseEntity.ok(bookService.getAllBooks(pageable));
    }

    //Gets a book, using the title of the book
    @GetMapping("/title")
    public ResponseEntity<List<BookDto>> searchBooksTitle(@Valid@RequestParam String title) {
        return ResponseEntity.ok(bookService.searchBooksByTitle(title));
    }

    //Gets a book by the name of the author
    @GetMapping("/author")
    public ResponseEntity<Page<BookDto>> searchBooksAuthor(@RequestParam String author, Pageable pageable) {
        return ResponseEntity.ok(bookService.searchBookByAuthor(author, pageable));
    }





}
