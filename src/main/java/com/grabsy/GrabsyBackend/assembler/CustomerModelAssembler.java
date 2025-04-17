package com.grabsy.GrabsyBackend.assembler;

import com.grabsy.GrabsyBackend.entity.users.Customer;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public class CustomerModelAssembler implements RepresentationModelAssembler<Customer, EntityModel<Customer>> {
    @Override
    public EntityModel<Customer> toModel(Customer customer) {
        return null; // TODO : Implement this properly
    }
}
