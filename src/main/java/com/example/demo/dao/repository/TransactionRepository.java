package com.example.demo.dao.repository;

import com.example.demo.dao.entity.TransactionEntity;
import com.example.demo.model.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    TransactionEntity findByIdAndTransactionType(Long id, TransactionType transactionType);
}
