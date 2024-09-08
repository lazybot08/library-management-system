package com.app.service;

import static com.app.converters.BookConverter.convertBookRequestDtoToBook;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.BookRequestDto;
import com.app.model.Author;
import com.app.model.Book;
import com.app.model.Card;
import com.app.repository.AuthorRepository;
import com.app.repository.BookRepository;
import com.app.repository.CardRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public String createBook(BookRequestDto bookRequestDto){
        Book book = new Book();
        book = convertBookRequestDtoToBook(bookRequestDto);

        // first get the car details from cardId
        Card card = cardRepository.findById(bookRequestDto.getCardId()).get();
        book.setCard(card);

        // next we need get the author deatils from authorId
        Author author = authorRepository.findById(bookRequestDto.getAuthorId()).get();
        book.setAuthor(author);

        bookRepository.save(book);
        return "Book saved successfully";
    }

    public List<Book> getAllBooks(){
        List<Book> bookList = bookRepository.findAll();
        return bookList;
    }

    public Book getBookById(int bookId){
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        Book book = bookOptional.get();
        return book;
    }
}
