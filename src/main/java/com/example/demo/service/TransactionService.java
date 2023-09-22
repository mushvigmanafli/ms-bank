package com.example.demo.service;

import com.example.demo.dao.entity.TransactionEntity;
import com.example.demo.model.enums.TransactionType;

import java.util.List;

public interface TransactionService {
    TransactionEntity createTransaction(Long id, TransactionType transactionType, double amount);

    TransactionEntity getTransaction(Long id, TransactionType transactionType);

    List<TransactionEntity> getTransactions(Long customerId);
}
