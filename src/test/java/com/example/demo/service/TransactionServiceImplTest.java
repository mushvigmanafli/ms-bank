package com.example.demo.service;

import com.example.demo.dao.entity.TransactionEntity;
import com.example.demo.dao.repository.TransactionRepository;
import com.example.demo.model.enums.TransactionType;
import com.example.demo.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionServiceImplTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateTransaction() {
        // Mocking the input data
        Long customerId = 1L;
        TransactionType transactionType = TransactionType.PURCHASE;
        double amount = 50.0;

        // Mocking the transaction entity
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setId(1L);
        transactionEntity.setCustomerId(customerId);
        transactionEntity.setTransactionType(transactionType);
        transactionEntity.setAmount(amount);

        // Mocking the repository behavior
        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(transactionEntity);

        // Calling the service method
        TransactionEntity result = transactionService.createTransaction(customerId, transactionType, amount);

        // Assertions
        assertNotNull(result);
        assertEquals(customerId, result.getCustomerId());
        assertEquals(transactionType, result.getTransactionType());
        assertEquals(amount, result.getAmount());
    }

    @Test
    void testGetTransaction() {
        // Mocking the input data
        Long transactionId = 1L;
        TransactionType transactionType = TransactionType.PURCHASE;

        // Mocking the transaction entity
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setId(transactionId);
        transactionEntity.setCustomerId(1L);
        transactionEntity.setTransactionType(transactionType);
        transactionEntity.setAmount(50.0);

        // Mocking the repository behavior
        when(transactionRepository.findByIdAndTransactionType(transactionId, transactionType))
                .thenReturn(transactionEntity);

        // Calling the service method
        TransactionEntity result = transactionService.getTransaction(transactionId, transactionType);

        // Assertions
        assertNotNull(result);
        assertEquals(transactionId, result.getId());
        assertEquals(transactionType, result.getTransactionType());
    }
}
