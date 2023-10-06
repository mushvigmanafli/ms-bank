package com.example.demo.service;


import com.example.demo.dao.entity.CustomerEntity;
import com.example.demo.dao.entity.TransactionEntity;
import com.example.demo.dao.repository.CustomerRepository;
import com.example.demo.model.dto.PaymentResponseDTO;
import com.example.demo.model.enums.TransactionType;
import com.example.demo.service.impl.PaymentServiceImpl;
import com.example.demo.util.PaymentServiceHelper;
import com.example.demo.validator.CustomerValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.time.LocalDateTime;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class PaymentServiceImplTest {

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private TransactionService transactionService;

    @Mock
    private CustomerService customerService;

    @Mock
    private CustomerValidator customerValidator;

    @Mock
    private PaymentServiceHelper paymentServiceHelper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testTopUpBalance() {
        // Mocking the input data
        Long customerId = 1L;
        double amount = 50.0;

        // Mocking the customer entity
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customerId);
        customerEntity.setBalance(100.0);

        // Mocking the transaction service behavior
        when(transactionService.createTransaction(customerId, TransactionType.TOP_UP, amount))
                .thenReturn(new TransactionEntity()); // You should return a proper TransactionEntity here

        // Mocking the customer service behavior
        when(customerService.getCustomerById(customerId)).thenReturn(customerEntity);

        // Mocking the paymentServiceHelper behavior
        when(paymentServiceHelper.formatDecimal(amount)).thenReturn(amount);

        // Calling the service method
        PaymentResponseDTO result = paymentService.topUpBalance(customerId, amount);

    }

    @Test
    void testMakePurchase() {
        // Mocking the input data
        Long customerId = 1L;
        double purchaseAmount = 30.0;

        // Mocking the customer entity
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customerId);
        customerEntity.setBalance(50.0);

        // Mocking the transaction service behavior
        when(transactionService.createTransaction(customerId, TransactionType.PURCHASE, purchaseAmount))
                .thenReturn(new TransactionEntity()); // You should return a proper TransactionEntity here

        // Mocking the customer service behavior
        when(customerService.getCustomerById(customerId)).thenReturn(customerEntity);

        // Mocking the customerValidator behavior
        doNothing().when(customerValidator).validateAmount(customerEntity.getBalance(), purchaseAmount);

        // Mocking the paymentServiceHelper behavior
        when(paymentServiceHelper.formatDecimal(purchaseAmount)).thenReturn(purchaseAmount);

        // Calling the service method
        PaymentResponseDTO result = paymentService.makePurchase(customerId, purchaseAmount);

    }

    @Test
    void testMakeRefund() {
        // Mocking the input data
        Long transactionId = 1L;

        // Mocking the transaction entity
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setId(transactionId);
        transactionEntity.setAmount(30.0);
        transactionEntity.setCustomerId(1L);

        // Mocking the transactionService behavior
        when(transactionService.getTransaction(transactionId, TransactionType.PURCHASE))
                .thenReturn(transactionEntity);

        // Mocking the customer service behavior
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(1L);
        customerEntity.setBalance(50.0);
        when(customerService.getCustomerById(transactionEntity.getCustomerId()))
                .thenReturn(customerEntity);

        double refundAmount = transactionEntity.getAmount() / PaymentServiceImpl.REFUND_PERCENTAGE;

        // Mocking the paymentServiceHelper behavior
        when(paymentServiceHelper.formatDecimal(refundAmount))
                .thenReturn(refundAmount);

        when(transactionService.createTransaction(1L, TransactionType.REFUND,
                refundAmount))
                .thenReturn(new TransactionEntity());

        // Calling the service method
        PaymentResponseDTO result = paymentService.makeRefund(transactionId);
    }


}

