package com.a00326153.library.controller;

import com.a00326153.library.dto.BookDto;
import com.a00326153.library.entity.Book;
import com.a00326153.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.createBook(book));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<BookDto>> getAllBooks(Pageable pageable){
        return ResponseEntity.ok(bookService.getAllBooks(pageable));
    }

    @GetMapping("/title")
    public ResponseEntity<List<BookDto>> searchBooksTitle(@RequestParam String title) {
        return ResponseEntity.ok(bookService.searchBooksByTitle(title));
    }

    @GetMapping("/author")
    public ResponseEntity<Page<BookDto>> searchBooksAuthor(@RequestParam String author, Pageable pageable) {
        return ResponseEntity.ok(bookService.searchBookByAuthor(author, pageable));
    }





}
