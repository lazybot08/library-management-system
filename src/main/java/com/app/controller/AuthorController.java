package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.app.dto.AuthorRequestDto;
import com.app.model.Author;
import com.app.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/create")
    public String createAuthor(@RequestBody AuthorRequestDto authorRequestDto){
        return authorService.createAuthor(authorRequestDto);
    }

    @GetMapping("/findAll")
    public List<Author> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @GetMapping("/find/{authorid}")
    public Author getAuthorById(@PathVariable("authorid") int authorId){
        return authorService.getAuthorById(authorId);
    }

    @PutMapping("/update/{authorid}")
    public String updateAuthor(@RequestBody AuthorRequestDto authorRequestDto, @PathVariable("authorid") int authorId){
        return authorService.updateAuthor(authorRequestDto, authorId);
    }
}
