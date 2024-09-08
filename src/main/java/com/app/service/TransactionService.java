package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.TransactionRequestDto;
import com.app.model.*;
import com.app.repository.BookRepository;
import com.app.repository.CardRepository;
import com.app.repository.TransactionRepository;

import static com.app.converters.TransactionConverter.convertTransactionRequestDtoToTransaction;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private BookRepository bookRepository;

    public String createTransaction(TransactionRequestDto transactionRequestDto){
        Transaction transaction = new Transaction();
        transaction = convertTransactionRequestDtoToTransaction(transactionRequestDto);

        // get card details from cardId
        Card card = cardRepository.findById(transactionRequestDto.getCardId()).get();
        transaction.setCard(card);

        // get book details from bookId
        Book book = bookRepository.findById(transactionRequestDto.getBookId()).get();
        transaction.setBook(book);

        transactionRepository.save(transaction);

        return "Transaction created";

    }

    public List<Transaction> getAllTransactions(){
        List<Transaction> transactionList = transactionRepository.findAll();
        return transactionList;
    }

    public Transaction getTransactionById(int transactionId){
        Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);
        Transaction transaction = transactionOptional.get();
        return transaction;
    }
}
