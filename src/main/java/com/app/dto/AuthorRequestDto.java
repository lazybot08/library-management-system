package com.app.dto;

import lombok.Data;

@Data
public class AuthorRequestDto {

    private String name;

    private int age;

    private String country;

    private double rating;
}
