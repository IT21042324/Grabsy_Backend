package com.grabsy.GrabsyBackend.controller;

import com.grabsy.GrabsyBackend.service.CustomerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;

/**
 * This class is a controller for the Customer entity, it handles HTTP requests related to customers.
 */

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
}
