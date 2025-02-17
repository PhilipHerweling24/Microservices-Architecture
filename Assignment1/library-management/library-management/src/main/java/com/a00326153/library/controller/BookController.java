package com.a00326153.library.controller;

import com.a00326153.library.dto.BookDto;
import com.a00326153.library.entity.Book;
import com.a00326153.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PutMapping("/books/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<BookDto> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/title")
    public List<BookDto> searchBooksTitle(@RequestParam String title) {
        return bookService.searchBooksByTitle(title);
    }

    @GetMapping("/author")
    public List<BookDto> searchBooksAuthor(@RequestParam String author) {
        return bookService.searchBookByAuthor(author);
    }





}
