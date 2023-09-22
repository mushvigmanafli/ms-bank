package com.example.demo.controller;

import com.example.demo.model.dto.PaymentResponseDTO;
import com.example.demo.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("${ms.root.url}" + "/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/top-up/{customerId}")
    public ResponseEntity<PaymentResponseDTO> topUpBalance(@PathVariable Long customerId, @RequestParam double amount) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(paymentService.topUpBalance(customerId , amount));
    }

    @PostMapping("/purchase/{customerId}")
    public ResponseEntity<PaymentResponseDTO> makePurchase(@PathVariable Long customerId, @RequestParam double amount) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(paymentService.makePurchase(customerId , amount));
    }

    @PostMapping("/refund/{transactionId}")
    public ResponseEntity<PaymentResponseDTO> makeRefund(@PathVariable Long transactionId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(paymentService.makeRefund(transactionId));
    }
}
