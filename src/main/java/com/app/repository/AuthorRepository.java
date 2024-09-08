package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Author;

@Repository // it does the database operations
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
