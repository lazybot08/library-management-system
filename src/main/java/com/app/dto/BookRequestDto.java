package com.app.dto;

import com.app.enums.Genre;

import lombok.Data;

@Data
public class BookRequestDto {

    private String name;

    private int pages;

    private Genre genre;

    private boolean issuedToStudent;

    private int cardId;

    private int authorId;
}
