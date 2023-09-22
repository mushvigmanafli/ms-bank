package com.example.demo.service;

import com.example.demo.dao.entity.CustomerEntity;
import com.example.demo.dao.repository.CustomerRepository;
import com.example.demo.model.dto.CustomerRequestDTO;
import com.example.demo.service.impl.CustomerServiceImpl;
import com.example.demo.validator.CustomerValidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerValidator customerValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateCustomer() {
        // Mocking the input DTO
        CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO();
        customerRequestDTO.setName("John Doe");

        // Mocking the customer entity
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(1L);
        customerEntity.setName("John Doe");

        // Mocking the repository behavior
        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(customerEntity);

        // Mocking the validator behavior
        doNothing().when(customerValidator).validateCustomerForCreation(any(CustomerRequestDTO.class));

        // Calling the service method
        CustomerEntity result = customerService.createCustomer(customerRequestDTO);

        // Assertions
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
    }

    @Test
    void testGetCustomerById() {
        // Mocking the customer entity
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(1L);
        customerEntity.setName("John Doe");

        // Mocking the repository behavior
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customerEntity));

        // Calling the service method
        CustomerEntity result = customerService.getCustomerById(1L);

        // Assertions
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
    }
}

