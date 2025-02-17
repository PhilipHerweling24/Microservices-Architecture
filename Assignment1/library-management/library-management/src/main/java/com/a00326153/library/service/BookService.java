package com.a00326153.library.service;

import com.a00326153.library.dto.BookDto;
import com.a00326153.library.entity.Book;
import com.a00326153.library.mapper.BookMapper;
import com.a00326153.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public BookDto createBook(Book book){
        return BookMapper.mapToBookDto(bookRepository.save(book));
    }

    public BookDto updateBook(Long id, Book updateBook){
        return bookRepository.findById(id).map(existingBook -> {
            existingBook.setTitle(updateBook.getTitle());
            existingBook.setAuthor((updateBook.getAuthor()));
            existingBook.setIsbn(updateBook.getIsbn());
            existingBook.setAvailableCopies(updateBook.getAvailableCopies());
            return BookMapper.mapToBookDto(bookRepository.save(existingBook));
        })
                .orElseThrow(() -> new RuntimeException(("Book not found with an ID of: "+id)));
    }

    public void deleteBook(Long id){
        if (! bookRepository.existsById(id)){
            throw new RuntimeException("Book not found with ID of: "+id);
        }
        bookRepository.deleteById(id);
    }


    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream().map(book -> BookMapper.mapToBookDto(book)).toList();
    }

    public List<BookDto> searchBooksByTitle(String title){
        return bookRepository.findByTitleContainingIgnoreCase(title).stream().map(book -> BookMapper.mapToBookDto(book)).toList();
    }

    public List<BookDto> searchBookByAuthor(String author){
        return bookRepository.findByAuthorContainingIgnoreCase(author).stream().map(book -> BookMapper.mapToBookDto(book)).toList();
    }
}
