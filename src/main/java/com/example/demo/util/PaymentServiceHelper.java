package com.example.demo.util;

import com.example.demo.model.dto.PaymentResponseDTO;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

@Component
public class PaymentServiceHelper {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public double formatDecimal(double amount){
        return Double.parseDouble(df.format(amount));
    }
    public PaymentResponseDTO createPaymentResponse(Long transactionId, Long customerId, double amount,
                                                    double balance, LocalDateTime date){
        return PaymentResponseDTO.builder()
                .transactionId(transactionId)
                .customerId(customerId)
                .amount(amount)
                .balance(balance)
                .date(date)
                .build();
    }


}
