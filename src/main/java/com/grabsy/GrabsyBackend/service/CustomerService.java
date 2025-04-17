package com.grabsy.GrabsyBackend.service;

import com.grabsy.GrabsyBackend.repository.CustomerRepository;
import com.grabsy.GrabsyBackend.repository.ProductRepository;
import com.grabsy.GrabsyBackend.assembler.ProductModelAssembler;
import org.springframework.stereotype.Service;

/**
 * This class is a service for the Customer entity, it contains business logic related to customers.
 */

// TODO

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    // constructor
    public CustomerService(CustomerRepository customerRepository, ProductRepository productRepository, ProductModelAssembler productAssembler) {
        this.customerRepository = customerRepository;
    }
}
