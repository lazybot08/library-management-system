package com.app.converters;

import com.app.dto.AuthorRequestDto;
import com.app.model.Author;

public class AuthorConverter {

    public static Author convertAuthorRequestDtoToAuthor(AuthorRequestDto authorRequestDto){
        Author author = Author.builder().name(authorRequestDto.getName()).age(authorRequestDto.getAge())
                .country(authorRequestDto.getCountry()).rating(authorRequestDto.getRating()).build();
        return author;
    }
}
