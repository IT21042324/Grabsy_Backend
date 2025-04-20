package com.grabsy.GrabsyBackend.controller;

import com.grabsy.GrabsyBackend.assembler.CustomerModelAssembler;
import com.grabsy.GrabsyBackend.entity.users.Customer;
import com.grabsy.GrabsyBackend.exception.CustomerNotFoundException;
import com.grabsy.GrabsyBackend.repository.CustomerRepository;
import com.grabsy.GrabsyBackend.service.CustomerService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

/**
 * This class is a controller for the Customer entity, it handles HTTP requests related to customers.
 */

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerModelAssembler customerModelAssembler;
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService, CustomerModelAssembler customerModelAssembler,
                              CustomerRepository customerRepository) {
        this.customerModelAssembler = customerModelAssembler;
        this.customerService = customerService;
    }

    /**
     * This method handles the HTTP GET request to retrieve all customers.
     * @return A collection of customers wrapped in a CollectionModel.
     */
    @GetMapping("/all")
    public CollectionModel<EntityModel<Customer>> findAllCustomers() {
        return customerModelAssembler.toCollectionModel(customerService.findAllCustomers());
    }

    /**
     * This method handles the HTTP GET request to retrieve a customer by their ID.
     * @param userId The ID of the customer to retrieve.
     * @return An EntityModel containing the customer.
     */
    @GetMapping("{userId}")
    public EntityModel<Customer> getCustomerById(@PathVariable String userId) {
        Customer customer = customerService.getCustomerById(userId);

        return customerModelAssembler.toModel(customer);
    }
}
