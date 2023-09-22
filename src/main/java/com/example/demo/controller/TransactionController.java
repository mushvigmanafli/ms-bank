package com.example.demo.controller;

import com.example.demo.dao.entity.TransactionEntity;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${ms.root.url}" + "/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{customerId}")
    public ResponseEntity<List<TransactionEntity>> getTransactions(@PathVariable Long customerId) {
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.getTransactions(customerId));
    }
}
