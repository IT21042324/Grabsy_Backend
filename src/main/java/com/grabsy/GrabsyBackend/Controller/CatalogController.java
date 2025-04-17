package com.grabsy.GrabsyBackend.controller;

import com.grabsy.GrabsyBackend.entity.Product;
import com.grabsy.GrabsyBackend.service.ProductService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {
    private ProductService productService;

    public CatalogController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * This method retrieves all products from the catalog and converts them to a CollectionModel<EntityModel<Product>> object.
     * @return the CollectionModel<EntityModel<Product>> object
     */
    @GetMapping
    public CollectionModel<EntityModel<Product>> browseCatalog(){
        return productService.findAll();
    }
}
