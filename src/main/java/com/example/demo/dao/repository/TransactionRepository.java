package com.example.demo.dao.repository;

import com.example.demo.dao.entity.TransactionEntity;
import com.example.demo.model.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    TransactionEntity findByIdAndTransactionType(Long id, TransactionType transactionType);

    List<TransactionEntity> findByCustomerId(Long customerId);
}
