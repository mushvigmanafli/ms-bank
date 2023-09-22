package com.example.demo.controller;

import com.example.demo.model.dto.PaymentResponseDTO;
import com.example.demo.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testTopUpBalance() {
        // Mocking the input data
        Long customerId = 1L;
        double amount = 50.0;

        // Mocking the payment response DTO
        PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO();
        paymentResponseDTO.setCustomerId(customerId);
        paymentResponseDTO.setBalance(100.0); // Mock the balance as needed

        // Mocking the service behavior
        when(paymentService.topUpBalance(customerId, amount)).thenReturn(paymentResponseDTO);

        // Calling the controller method
        ResponseEntity<PaymentResponseDTO> responseEntity = paymentController.topUpBalance(customerId, amount);

        // Assertions
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        PaymentResponseDTO responseBody = responseEntity.getBody();
        assertEquals(customerId, responseBody.getCustomerId());
    }

    @Test
    void testMakePurchase() {
        // Mocking the input data
        Long customerId = 1L;
        double amount = 30.0;

        // Mocking the payment response DTO
        PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO();
        paymentResponseDTO.setCustomerId(customerId);
        paymentResponseDTO.setBalance(70.0); // Mock the balance as needed

        // Mocking the service behavior
        when(paymentService.makePurchase(customerId, amount)).thenReturn(paymentResponseDTO);

        // Calling the controller method
        ResponseEntity<PaymentResponseDTO> responseEntity = paymentController.makePurchase(customerId, amount);

        // Assertions
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        PaymentResponseDTO responseBody = responseEntity.getBody();
        assertEquals(customerId, responseBody.getCustomerId());
    }

    @Test
    void testMakeRefund() {
        // Mocking the input data
        Long transactionId = 1L;

        // Mocking the payment response DTO
        PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO();
        paymentResponseDTO.setTransactionId(transactionId);
        paymentResponseDTO.setCustomerId(1L);
        paymentResponseDTO.setBalance(80.0); // Mock the balance as needed

        // Mocking the service behavior
        when(paymentService.makeRefund(transactionId)).thenReturn(paymentResponseDTO);

        // Calling the controller method
        ResponseEntity<PaymentResponseDTO> responseEntity = paymentController.makeRefund(transactionId);

        // Assertions
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        PaymentResponseDTO responseBody = responseEntity.getBody();
        assertEquals(transactionId, responseBody.getTransactionId());
    }

}
