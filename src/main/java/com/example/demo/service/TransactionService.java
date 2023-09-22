package com.example.demo.service;

import com.example.demo.dao.entity.TransactionEntity;
import com.example.demo.model.enums.TransactionType;

public interface TransactionService {
    TransactionEntity createTransaction(Long id, TransactionType transactionType, double amount);

    TransactionEntity getTransaction(Long id, TransactionType transactionType);
}
