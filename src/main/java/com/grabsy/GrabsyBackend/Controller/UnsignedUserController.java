package com.grabsy.GrabsyBackend.controller;

import com.grabsy.GrabsyBackend.assembler.CustomerModelAssembler;
import com.grabsy.GrabsyBackend.dto.CustomerDto;
import com.grabsy.GrabsyBackend.entity.users.Customer;
import com.grabsy.GrabsyBackend.repository.CustomerRepository;
import com.grabsy.GrabsyBackend.service.CustomerService;
import com.grabsy.GrabsyBackend.service.UnsignedUserService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unsigned-user")
public class UnsignedUserController {
    private final UnsignedUserService unsignedUserService;
    private final CustomerService customerService;
    private final CustomerModelAssembler customerModelAssembler;
    private final CustomerRepository customerRepository;

    public UnsignedUserController(UnsignedUserService unsignedUserService, CustomerService customerService, CustomerModelAssembler customerModelAssembler, CustomerRepository customerRepository) {
        this.unsignedUserService = unsignedUserService;
        this.customerService = customerService;
        this.customerModelAssembler = customerModelAssembler;
        this.customerRepository = customerRepository;
    }

    /**
     * This method handles the HTTP POST request to register a new customer.
     * @param customerDto The DTO containing the customer's information.
     * @return A ResponseEntity containing the created customer wrapped in an EntityModel.
     * @throws IllegalAccessException If the email format is invalid.
     */
    @PostMapping("/register/customer")
    public ResponseEntity<EntityModel<Customer>> registerCustomer(@RequestBody CustomerDto customerDto) throws IllegalAccessException {
        Customer customer = customerService.registerCustomer(customerDto);

        EntityModel<Customer> entityModel = customerModelAssembler.toModel(customerRepository.save(customer));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }
}
