package com.example.demo.service.impl;

import com.example.demo.dao.entity.CustomerEntity;
import com.example.demo.dao.repository.CustomerRepository;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.model.dto.CustomerRequestDTO;
import com.example.demo.service.CustomerService;
import com.example.demo.validator.CustomerValidator;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private static final Logger logger = Logger.getLogger(CustomerServiceImpl.class.getName());

    private final CustomerRepository customerRepository;
    private final CustomerValidator customerValidator;

    @Override
    public CustomerEntity createCustomer(CustomerRequestDTO customerRequestDTO) {
        logger.info("createCustomer start. customerRequestDTO: " + customerRequestDTO);

        // Validate the customer object
        customerValidator.validateCustomerForCreation(customerRequestDTO);

        logger.info("createCustomer end. customerRequestDTO: " + customerRequestDTO);

        // Save the customer
        return customerRepository.save(CustomerMapper.INSTANCE.dtoToEntity(customerRequestDTO));
    }

    @Override
    public CustomerEntity getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Override
    public List<CustomerEntity> getCustomers() {
        return customerRepository.findAll();
    }
}
