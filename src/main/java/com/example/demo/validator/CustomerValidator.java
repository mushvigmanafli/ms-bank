package com.example.demo.validator;

import com.example.demo.model.dto.CustomerRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidator {

    public void validateCustomerForCreation(CustomerRequestDTO customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer object cannot be null.");
        }

        if (customer.getName() == null || customer.getSurname() == null
                || customer.getGsmNumber() == null) {
            throw new IllegalArgumentException("Name, Surname, Birth Date, and GSM Number are required.");
        }

        if (customer.getBalance() != 100.0) {
            throw new IllegalArgumentException("Initial balance must be 100 AZN.");
        }
    }

    public void validateAmount(double currentBalance, double amount) {
        if (currentBalance < amount) {
            throw new RuntimeException("Insufficient balance");
        }
    }
}
