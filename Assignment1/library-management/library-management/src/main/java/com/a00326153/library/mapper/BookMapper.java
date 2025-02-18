package com.a00326153.library.mapper;

import com.a00326153.library.dto.BookDto;
import com.a00326153.library.entity.Book;


public class BookMapper {

    public static BookDto mapToBookDto(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setBookId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.addHateoasLinks();
        return bookDto;
    }

}
