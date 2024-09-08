package com.app.converters;

import com.app.dto.BookRequestDto;
import com.app.model.Book;

public class BookConverter {

    public static Book convertBookRequestDtoToBook(BookRequestDto bookRequestDto){
        Book book = Book.builder().name(bookRequestDto.getName()).pages(bookRequestDto.getPages())
                .genre(bookRequestDto.getGenre()).issuedToStudent(bookRequestDto.isIssuedToStudent()).build();
        return  book;
    }
}
