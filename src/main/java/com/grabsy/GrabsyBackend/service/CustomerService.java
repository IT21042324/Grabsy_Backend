package com.grabsy.GrabsyBackend.service;

import com.grabsy.GrabsyBackend.repository.CustomerRepository;

/**
 * This class is a service for the Customer entity, it contains business logic related to customers.
 */

// TODO

public class CustomerService {
    private final CustomerRepository customerRepository;

    // constructor
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


}
