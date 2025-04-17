package com.grabsy.GrabsyBackend.service;

import com.grabsy.GrabsyBackend.entity.Product;
import com.grabsy.GrabsyBackend.repository.CustomerRepository;
import com.grabsy.GrabsyBackend.repository.ProductRepository;
import com.grabsy.GrabsyBackend.assembler.ProductModelAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

/**
 * This class is a service for the Customer entity, it contains business logic related to customers.
 */

// TODO

public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ProductModelAssembler productAssembler;

    // constructor
    public CustomerService(CustomerRepository customerRepository, ProductRepository productRepository, ProductModelAssembler productAssembler) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.productAssembler = productAssembler;
    }

    // methods

    /**
     * This method retrieves all products from the catalog and converts them to a CollectionModel<EntityModel<Product>> object.
     * @return the CollectionModel<EntityModel<Product>> object
     */
    public CollectionModel<EntityModel<Product>> browseCatalog(){
        return productAssembler.toCollectionModel(productRepository.findAll());
    }
}
