package com.example.demo.service;

import com.example.demo.dao.entity.CustomerEntity;
import com.example.demo.model.dto.CustomerRequestDTO;

public interface CustomerService {
    CustomerEntity createCustomer(CustomerRequestDTO customer);

    CustomerEntity getCustomerById(Long customerId);
}
