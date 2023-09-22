package com.example.demo.util;

import com.example.demo.model.dto.PaymentResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentServiceHelperTest {

    private PaymentServiceHelper paymentServiceHelper;

    @BeforeEach
    void setUp() {
        paymentServiceHelper = new PaymentServiceHelper();
    }

    @Test
    void testCreatePaymentResponse() {
        // Sample data
        Long transactionId = 1L;
        Long customerId = 2L;
        double amount = 50.0;
        double balance = 100.0;
        LocalDateTime date = LocalDateTime.now();

        // Call the method
        PaymentResponseDTO result = paymentServiceHelper.createPaymentResponse(transactionId, customerId, amount, balance, date);

        // Verify expected results
        assertEquals(transactionId, result.getTransactionId());
        assertEquals(customerId, result.getCustomerId());
        assertEquals(amount, result.getAmount());
        assertEquals(balance, result.getBalance());
        assertEquals(date, result.getDate());
    }

    @Test
    void testFormatDecimal() {
        // Sample double number
        double amount = 55.555;

        // Call the method
        double formattedAmount = paymentServiceHelper.formatDecimal(amount);

        // Expected result: The number rounded to only two decimal places
        double expectedFormattedAmount = Double.parseDouble(new DecimalFormat("0.00").format(amount));

        // Verify expected results
        assertEquals(expectedFormattedAmount, formattedAmount);
    }
}
