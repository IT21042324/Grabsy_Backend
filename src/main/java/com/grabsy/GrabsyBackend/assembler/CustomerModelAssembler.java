package com.grabsy.GrabsyBackend.assembler;

import com.grabsy.GrabsyBackend.controller.CustomerController;
import com.grabsy.GrabsyBackend.entity.users.Customer;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * This class is responsible for converting Customer entities into EntityModel objects.
 */

@Component
public class CustomerModelAssembler implements RepresentationModelAssembler<Customer, EntityModel<Customer>> {
    @Override
    public EntityModel<Customer> toModel(Customer customer) {
        return EntityModel.of(customer,
                linkTo(methodOn(CustomerController.class).getCustomerById(customer.getUserId())).withSelfRel(),
                linkTo(methodOn(CustomerController.class).findAllCustomers()).withRel("customers"));
    }

    @Override
    public CollectionModel<EntityModel<Customer>> toCollectionModel(Iterable<? extends Customer> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
