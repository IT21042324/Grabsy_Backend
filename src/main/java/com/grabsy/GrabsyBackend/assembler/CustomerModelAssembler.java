package com.grabsy.GrabsyBackend.assembler;

import com.grabsy.GrabsyBackend.controller.user.CustomerController;
import com.grabsy.GrabsyBackend.entity.users.Customer;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * This class is responsible for converting Customer entities into EntityModel objects.
 */

@Component
public class CustomerModelAssembler extends UserModelAssembler<Customer> {

    /**
     * This method converts a Customer entity into an EntityModel object.
     * @param customer The Customer entity to convert.
     * @return An EntityModel containing the Customer entity and its links.
     */
    @Override
    public EntityModel<Customer> toModel(Customer customer) {
        return EntityModel.of(customer,
                linkTo(methodOn(CustomerController.class).getById(customer.getUserId())).withSelfRel(),
                linkTo(methodOn(CustomerController.class).findAll()).withRel("customers"));
    }
}
