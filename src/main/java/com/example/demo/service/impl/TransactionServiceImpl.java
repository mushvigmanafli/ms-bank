package com.example.demo.service.impl;

import com.example.demo.dao.entity.TransactionEntity;
import com.example.demo.dao.repository.TransactionRepository;
import com.example.demo.model.enums.TransactionType;
import com.example.demo.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private static final Logger logger = Logger.getLogger(CustomerServiceImpl.class.getName());

    private final TransactionRepository transactionRepository;

    @Override
    public TransactionEntity createTransaction(Long id, TransactionType transactionType, double amount) {
        logger.info("createTransaction start. customerId: " + id);
        // Create a transaction
        var entity = TransactionEntity.builder()
                .customerId(id)
                .transactionType(transactionType)
                .amount(amount)
                .build();

        logger.info("createTransaction end. customerId: " + id);
        return transactionRepository.save(entity);
    }

    @Override
    public TransactionEntity getTransaction(Long id, TransactionType transactionType) {
        logger.info("getTransaction start. customerId: " + id);

        // Get transaction
        var transactionEntity = transactionRepository.findByIdAndTransactionType(id, transactionType);

        logger.info("getTransaction end. customerId: " + id);
        return transactionEntity;
    }

    @Override
    public List<TransactionEntity> getTransactions(Long customerId) {
       return transactionRepository.findByCustomerId(customerId);
    }
}
