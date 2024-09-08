package com.app.service;

import static com.app.converters.AuthorConverter.convertAuthorRequestDtoToAuthor;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.AuthorRequestDto;
import com.app.model.Author;
import com.app.repository.AuthorRepository;

@Service // it contains business logic
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public String createAuthor(AuthorRequestDto authorRequestDto){
        Author author = new Author();
        author = convertAuthorRequestDtoToAuthor(authorRequestDto);
        authorRepository.save(author);
        return "Author saved successfully";
    }

    public List<Author> getAllAuthors(){
        List<Author> authorList = authorRepository.findAll();
        return authorList;
    }

    public Author getAuthorById(int authorId){
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        Author author = authorOptional.get();
        return author;
    }

    public String updateAuthor(AuthorRequestDto authorRequestDto, int authorId){
        Author authorFromDb = authorRepository.findById(authorId).get();
        if(authorFromDb!=null){
            authorFromDb.setName(authorRequestDto.getName());
            authorFromDb.setAge(authorRequestDto.getAge());
            authorFromDb.setCountry(authorRequestDto.getCountry());
            authorFromDb.setRating(authorRequestDto.getRating());

            authorRepository.save(authorFromDb);
            return "Author updated Successfully";
        } else{
            return "Author not found to update";
        }
    }
}
