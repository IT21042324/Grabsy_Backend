package com.grabsy.GrabsyBackend.controller;

import com.grabsy.GrabsyBackend.entity.Product;
import com.grabsy.GrabsyBackend.response.ProductResponse;
import com.grabsy.GrabsyBackend.service.ProductService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {
    private final ProductService productService;

    public CatalogController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * This method retrieves all products from the catalog and converts them to a CollectionModel<EntityModel<Product>> object.
     * @return the CollectionModel<EntityModel<Product>> object
     */
    @GetMapping
    public CollectionModel<EntityModel<ProductResponse>> browseCatalog(){
        return productService.findAll();
    }

    // TODO : Complete this method once relevant productSerivce search method has been implemented
    /**
     * This method retrieves products from the catalog based on a search query and converts them to a CollectionModel<EntityModel<Product>> object.
     * @return the CollectionModel<EntityModel<Product>> object
     */
    // @GetMapping("/search")
    // public CollectionModel<EntityModel<Product>> searchProducts(@RequestParam Optional<String> keyword){
    //     return productService.searchProducts(keyword);
    // }

    // TODO : Implement filterProducts() method
}
