package com.a00326153.library.service;

import com.a00326153.library.dto.BookDto;
import com.a00326153.library.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    BookDto createBook(Book book);

    BookDto updateBook(Long id, Book updateBook);

    void deleteBook(Long id);

    Page<BookDto> getAllBooks(Pageable pageable);

    List<BookDto> searchBooksByTitle(String title);

    Page<BookDto> searchBookByAuthor(String author, Pageable pageable);
}
