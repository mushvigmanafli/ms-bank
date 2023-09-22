package com.example.demo.controller;

import com.example.demo.dao.entity.CustomerEntity;
import com.example.demo.model.dto.CustomerRequestDTO;
import com.example.demo.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateCustomer() {
        // Mocking the input data
        CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO();
        customerRequestDTO.setName("John Doe");

        // Mocking the customer entity
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(1L);
        customerEntity.setName("John Doe");

        // Mocking the service behavior
        when(customerService.createCustomer(customerRequestDTO)).thenReturn(customerEntity);

        // Calling the controller method
        ResponseEntity<CustomerEntity> responseEntity = customerController.createCustomer(customerRequestDTO);

        // Assertions
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        CustomerEntity responseCustomer = responseEntity.getBody();
        assertEquals(customerEntity.getId(), responseCustomer.getId());
        assertEquals(customerEntity.getName(), responseCustomer.getName());
    }
}
