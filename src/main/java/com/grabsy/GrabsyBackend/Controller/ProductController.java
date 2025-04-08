package com.grabsy.GrabsyBackend.Controller;

import com.grabsy.GrabsyBackend.Entity.Product;
import com.grabsy.GrabsyBackend.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products/")
public class ProductController {
    private final ProductService service;

    public ProductController(@Autowired ProductService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public EntityModel<Product> findById(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping
    public CollectionModel<EntityModel<Product>> findAll() {
        return service.findAll();
    }

    @PostMapping
    public EntityModel<Product> save(@RequestBody Product product) {
        return service.save(product);
    }

    @PatchMapping("/update/{id}")
    public EntityModel<Product> findByIdAndUpdate(@PathVariable String id, @RequestBody Product productToSave) {
        return service.findByIdAndUpdate(id, productToSave);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteById(@PathVariable String id) {
        return service.deleteById(id);
    }
}
