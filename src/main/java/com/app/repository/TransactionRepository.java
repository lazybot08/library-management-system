package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
}
