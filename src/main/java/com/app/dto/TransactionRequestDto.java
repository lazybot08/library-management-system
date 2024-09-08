package com.app.dto;

import com.app.enums.TransactionStatus;

import lombok.Data;

@Data
public class TransactionRequestDto {

    private TransactionStatus transactionStatus;

    private double fine;

    private boolean issueOperation;

    private int cardId;

    private int bookId;
}
