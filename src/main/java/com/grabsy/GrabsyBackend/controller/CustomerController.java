package com.grabsy.GrabsyBackend.controller;

import com.grabsy.GrabsyBackend.assembler.CustomerModelAssembler;
import com.grabsy.GrabsyBackend.entity.users.Customer;
import com.grabsy.GrabsyBackend.service.CustomerService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class is a controller for the Customer entity, it handles HTTP requests related to customers.
 */

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerModelAssembler customerModelAssembler;
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService, CustomerModelAssembler customerModelAssembler) {
        this.customerModelAssembler = customerModelAssembler;
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public CollectionModel<EntityModel<Customer>> findAllCustomers() {
        return customerModelAssembler.toCollectionModel(customerService.findAllCustomers());
    }

    @GetMapping("{userId}")
    public EntityModel<Customer> getCustomerById(@PathVariable String userId) {
        Customer customer = customerService.getCustomerById(userId);

        return customerModelAssembler.toModel(customer);
    }

    @DeleteMapping("/remove/{userId}")
    public ResponseEntity<?> removeCustomer(@PathVariable String userId){
        customerService.removeCustomer(userId);

        return ResponseEntity.noContent().build();
    }
}
