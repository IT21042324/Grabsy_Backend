package com.grabsy.GrabsyBackend.controller;

import com.grabsy.GrabsyBackend.assembler.CustomerModelAssembler;
import com.grabsy.GrabsyBackend.dto.SignedUserDto;
import com.grabsy.GrabsyBackend.entity.users.Customer;
import com.grabsy.GrabsyBackend.exception.CustomerNotFoundException;
import com.grabsy.GrabsyBackend.repository.CustomerRepository;
import com.grabsy.GrabsyBackend.service.CustomerService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class is a controller for the Customer entity, it handles HTTP requests related to customers.
 */

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerModelAssembler customerModelAssembler;
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerService customerService, CustomerModelAssembler customerModelAssembler,
                              CustomerRepository customerRepository) {
        this.customerService = customerService;
        this.customerModelAssembler = customerModelAssembler;
        this.customerRepository = customerRepository;
    }

    /**
     * This method handles the HTTP GET request to retrieve all customers.
     * @return A collection of customers wrapped in a CollectionModel.
     */
    @GetMapping("/all")
    public CollectionModel<EntityModel<Customer>> findAllCustomers() {
        return customerModelAssembler.toCollectionModel(customerRepository.findAll());
    }

    /**
     * This method handles the HTTP GET request to retrieve a customer by their ID.
     * @param userId The ID of the customer to retrieve.
     * @return An EntityModel containing the customer.
     */
    @GetMapping("{userId}")
    public EntityModel<Customer> getCustomerById(@PathVariable String userId) {
        Customer customer = customerRepository.findById(userId)
                .orElseThrow(() -> new CustomerNotFoundException(userId));

        return customerModelAssembler.toModel(customer);
    }

    /**
     * This method handles the HTTP POST request to register a new customer.
     * @param signedUserDto The DTO containing the customer's information.
     * @return A ResponseEntity containing the created customer wrapped in an EntityModel.
     * @throws IllegalAccessException If the email format is invalid.
     */
    @PostMapping("/register")
    public ResponseEntity<EntityModel<Customer>> registerCustomer(@RequestBody SignedUserDto signedUserDto) throws IllegalAccessException {
        Customer customer = customerService.registerCustomer(signedUserDto);

        EntityModel<Customer> entityModel = customerModelAssembler.toModel(customerRepository.save(customer));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }
}
