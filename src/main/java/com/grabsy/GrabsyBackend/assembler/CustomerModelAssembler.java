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

    /**
     * This method converts a Customer entity into an EntityModel object.
     * @param customer The Customer entity to convert.
     * @return An EntityModel containing the Customer entity and its links.
     */
    @Override
    public EntityModel<Customer> toModel(Customer customer) {
        return EntityModel.of(customer,
                linkTo(methodOn(CustomerController.class).getCustomerById(customer.getUserId())).withSelfRel(),
                linkTo(methodOn(CustomerController.class).findAllCustomers()).withRel("customers"));
    }

    /**
     * This method converts a collection of Customer entities into a CollectionModel object.
     * @param entities The collection of Customer entities to convert.
     * @return A CollectionModel containing the EntityModels of the Customer entities.
     */
    @Override
    public CollectionModel<EntityModel<Customer>> toCollectionModel(Iterable<? extends Customer> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
