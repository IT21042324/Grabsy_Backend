package com.grabsy.GrabsyBackend.assembler;

import com.grabsy.GrabsyBackend.entity.Product;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * This class is a model assembler for the Product entity, it converts Product entities to EntityModel<Product> objects.
 */

@Component // @Component allows the assembler to be injected wherever needed
public class ProductModelAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>> {

    /**
     * This method converts a Product entity to an EntityModel<Product> object.
     * @param product the Product entity
     * @return the EntityModel<Product> object
     */
    @Override
    public EntityModel<Product> toModel(Product product) {
        return EntityModel.of(product,
                linkTo(methodOn(com.grabsy.GrabsyBackend.controller.ProductController.class).findById(product.getId())).withSelfRel(),
                linkTo(methodOn(com.grabsy.GrabsyBackend.controller.ProductController.class).findAll()).withRel("products"));
    }

    /**
     * This method converts a collection of Product entities to a CollectionModel<EntityModel<Product>> object.
     * @param products the collection of Product entities
     * @return the CollectionModel<EntityModel<Product>> object
     */
    @Override
    public CollectionModel<EntityModel<Product>> toCollectionModel(Iterable<? extends Product> products) {
        return RepresentationModelAssembler.super.toCollectionModel(products);
    }
}
