package com.a00326153.library.serviceImpl;

import com.a00326153.library.constants.ServiceConstants;
import com.a00326153.library.dto.BookDto;
import com.a00326153.library.entity.Book;
import com.a00326153.library.exception.EntityAlreadyExistsException;
import com.a00326153.library.exception.ResourceNotFoundException;
import com.a00326153.library.mapper.BookMapper;
import com.a00326153.library.repository.BookRepository;
import com.a00326153.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public BookDto createBook(Book book){
        if(bookRepository.existsByIsbn(book.getIsbn())){
            throw new EntityAlreadyExistsException("Book with ISBN "+ book.getIsbn()+ " Already Exists");
        }
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
                .orElseThrow(() -> new ResourceNotFoundException((ServiceConstants.STATUS_417 + ServiceConstants.MESSAGE_417_UPDATE)));
    }

    public void deleteBook(Long id){
        if (! bookRepository.existsById(id)){
            throw new ResourceNotFoundException(ServiceConstants.STATUS_417+ ServiceConstants.MESSAGE_417_DELETE);
        }
        bookRepository.deleteById(id);
    }


    public Page<BookDto> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).map(book -> BookMapper.mapToBookDto(book));
    }

    public List<BookDto> searchBooksByTitle(String title){
        return bookRepository.findByTitleContainingIgnoreCase(title).stream().map(book -> BookMapper.mapToBookDto(book)).toList();
    }

    public Page<BookDto> searchBookByAuthor(String author, Pageable pageable){
        return bookRepository.findByAuthorContainingIgnoreCase(author, pageable).map(book -> BookMapper.mapToBookDto(book));
    }
}
