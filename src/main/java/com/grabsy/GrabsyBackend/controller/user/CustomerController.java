package com.grabsy.GrabsyBackend.controller.user;

import com.grabsy.GrabsyBackend.assembler.CustomerModelAssembler;
import com.grabsy.GrabsyBackend.dto.AddCustomerShippingAddressDto;
import com.grabsy.GrabsyBackend.entity.users.Customer;
import com.grabsy.GrabsyBackend.ervice.user.CustomerService;
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
    private final CustomerModelAssembler customerModelAssembler;
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService, CustomerModelAssembler customerModelAssembler) {
        this.customerModelAssembler = customerModelAssembler;
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public CollectionModel<EntityModel<Customer>> findAll() {
        return customerModelAssembler.toCollectionModel(customerService.findAll());
    }

    @GetMapping("{userId}")
    public EntityModel<Customer> getById(@PathVariable String userId) {
        Customer customer = customerService.getById(userId);

        return customerModelAssembler.toModel(customer);
    }

    @DeleteMapping("/remove/{userId}")
    public ResponseEntity<?> removeById(@PathVariable String userId){
        customerService.removeById(userId);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/shipping-address")
    public ResponseEntity<EntityModel<Customer>> updateShippingAddress(@RequestBody AddCustomerShippingAddressDto dto){
        Customer customer = customerService.updateShippingAddress(dto);

        EntityModel<Customer> entityModel = customerModelAssembler.toModel(customer);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }
}
