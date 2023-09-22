package com.example.demo.service.impl;

import com.example.demo.dao.entity.CustomerEntity;
import com.example.demo.dao.repository.CustomerRepository;
import com.example.demo.model.dto.PaymentResponseDTO;
import com.example.demo.model.enums.TransactionType;
import com.example.demo.service.CustomerService;
import com.example.demo.service.PaymentService;
import com.example.demo.service.TransactionService;
import com.example.demo.util.PaymentServiceHelper;
import com.example.demo.validator.CustomerValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private static final Logger logger = Logger.getLogger(CustomerServiceImpl.class.getName());

    public static final double REFUND_PERCENTAGE = 3;
    private final CustomerRepository customerRepository;
    private final TransactionService transactionService;
    private final CustomerService customerService;
    private final CustomerValidator customerValidator;
    private final PaymentServiceHelper paymentServiceHelper;

    @Override
    public PaymentResponseDTO topUpBalance(Long customerId, double amount) {
        logger.info("topUpBalance start. customerId:" + customerId);

        amount = paymentServiceHelper.formatDecimal(amount);

        CustomerEntity customer = customerService.getCustomerById(customerId);

        double newBalance = customer.getBalance() + amount;
        customer.setBalance(newBalance);

        // Create a top-up transaction
        var transaction= transactionService.createTransaction(customerId, TransactionType.TOP_UP, amount);

        customerRepository.save(customer);

        logger.info("topUpBalance end. customerId: " + customerId);

        return paymentServiceHelper.createPaymentResponse(transaction.getId(), customerId, transaction.getAmount(),
                customer.getBalance(), transaction.getCreatedAt());
    }

    @Override
    public PaymentResponseDTO makePurchase(Long customerId, double amount) {
        logger.info("makePurchase start. customerId: " + customerId);

        amount = paymentServiceHelper.formatDecimal(amount);

        CustomerEntity customer = customerService.getCustomerById(customerId);

        // Validate the purchase
        customerValidator.validatePurchase(customer.getBalance(), amount);

        customer.setBalance(customer.getBalance() - amount);

        // Create a purchase transaction
        var transaction = transactionService.createTransaction(customerId, TransactionType.PURCHASE, amount);

        // Update customer balance
        customerRepository.save(customer);

        logger.info("makePurchase end. customerId: " + customerId);

        return paymentServiceHelper.createPaymentResponse(transaction.getId(), customerId, transaction.getAmount(),
                customer.getBalance(), transaction.getCreatedAt());
    }

    @Override
    public PaymentResponseDTO makeRefund(Long transactionId) {
        logger.info("makeRefund start. transactionId: " + transactionId);

        var transactionEntity = transactionService.getTransaction(transactionId, TransactionType.PURCHASE);

        // Calculate refund amount
        var refundAmount = paymentServiceHelper.formatDecimal(
                transactionEntity.getAmount() / REFUND_PERCENTAGE);

        // Create a refund transaction
        var transaction = transactionService.createTransaction(transactionEntity.getCustomerId(),
                TransactionType.REFUND, refundAmount);
        var customer  = customerService.getCustomerById(transactionEntity.getCustomerId());
        customer.setBalance(customer.getBalance() + refundAmount);

        // Update customer balance
        customerRepository.save(customer);

        logger.info("makeRefund end. transactionId: " + transactionId);

        return paymentServiceHelper.createPaymentResponse(transaction.getId(), customer.getId(), transaction.getAmount(),
                customer.getBalance(), transaction.getCreatedAt());
    }
}
