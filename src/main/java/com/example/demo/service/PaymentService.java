package com.example.demo.service;

import com.example.demo.model.dto.PaymentResponseDTO;

public interface PaymentService {
    PaymentResponseDTO topUpBalance(Long customerId, double amount);

    PaymentResponseDTO makePurchase(Long customerId, double amount);

    PaymentResponseDTO makeRefund(Long transactionId);
}
