package com.grabsy.GrabsyBackend.Controller;

import com.grabsy.GrabsyBackend.Entity.Product;
import com.grabsy.GrabsyBackend.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/")
public class ProductController {
    private final ProductService service;

    public ProductController(@Autowired ProductService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Product>> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Product>>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<EntityModel<Product>> save(@RequestBody Product product) {
        EntityModel<Product> savedProduct = service.save(product);
        return ResponseEntity.created(savedProduct.getRequiredLink("self").toUri()).body(savedProduct);
    }

    @PatchMapping("/update/{id}")
    public EntityModel<Product> findByIdAndUpdate(@PathVariable String id, @RequestBody Product productToSave) {
        return service.findByIdAndUpdate(id, productToSave);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteById(@PathVariable String id) {
        return service.deleteById(id);
    }

    @GetMapping("/")
    public CollectionModel<EntityModel<Product>> findAllById(@RequestParam List<String> id) {
        return service.findAllById(id);
    }

    @PostMapping("/")
    public CollectionModel<EntityModel<Product>> findAllByIdThroughPostBodyRequest(@RequestBody List<String> ids) {
        return service.findAllById(ids);
    }
}
