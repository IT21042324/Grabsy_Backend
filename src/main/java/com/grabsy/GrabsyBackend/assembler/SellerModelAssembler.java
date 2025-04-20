package com.grabsy.GrabsyBackend.assembler;

import com.grabsy.GrabsyBackend.controller.SellerController;
import com.grabsy.GrabsyBackend.entity.users.Seller;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * This class is responsible for converting Seller entities into EntityModel objects.
 */

@Component
public class SellerModelAssembler implements RepresentationModelAssembler<Seller, EntityModel<Seller>> {

    /**
     * This method converts a Seller entity into an EntityModel object.
     * @param entity
     * @return An EntityModel containing the Seller entity and its links.
     */
    @Override
    public EntityModel<Seller> toModel(Seller entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(SellerController.class).getSellerById(entity.getUserId())).withSelfRel(),
                linkTo(methodOn(SellerController.class).findAllSellers()).withRel("sellers"));
    }

    /**
     * This method converts a collection of Seller entities into a CollectionModel object.
     * @param entities
     * @return A CollectionModel containing the EntityModels of the Seller entities.
     */
    @Override
    public CollectionModel<EntityModel<Seller>> toCollectionModel(Iterable<? extends Seller> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
