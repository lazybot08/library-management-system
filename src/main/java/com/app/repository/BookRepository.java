package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
