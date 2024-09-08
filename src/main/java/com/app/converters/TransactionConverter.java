package com.app.converters;

import java.util.UUID;

import com.app.dto.TransactionRequestDto;
import com.app.model.Transaction;

public class TransactionConverter {

    public static Transaction convertTransactionRequestDtoToTransaction(TransactionRequestDto transactionRequestDto){
        Transaction transaction = Transaction.builder().transactionStatus(transactionRequestDto.getTransactionStatus()).fine(transactionRequestDto.getFine())
                .transactionId(UUID.randomUUID().toString()).isIssueOperation(transactionRequestDto.isIssueOperation()).build();
        return transaction;
    }
}
