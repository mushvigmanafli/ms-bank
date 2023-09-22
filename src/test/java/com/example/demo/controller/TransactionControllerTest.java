package com.example.demo.controller;

import com.example.demo.dao.entity.TransactionEntity;
import com.example.demo.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TransactionControllerTest {

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetTransactions() {
        // Mocking the input data
        Long customerId = 1L;

        // Mocking the expected transaction list
        List<TransactionEntity> expectedTransactions = new ArrayList<>();
        expectedTransactions.add(new TransactionEntity());
        expectedTransactions.add(new TransactionEntity());

        // Mocking the transaction service behavior
        when(transactionService.getTransactions(customerId)).thenReturn(expectedTransactions);

        // Calling the controller method
        ResponseEntity<List<TransactionEntity>> response = transactionController.getTransactions(customerId);

        // Asserting the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedTransactions, response.getBody());
    }
}
