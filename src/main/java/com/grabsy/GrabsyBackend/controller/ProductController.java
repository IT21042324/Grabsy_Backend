package com.grabsy.GrabsyBackend.controller;

import com.grabsy.GrabsyBackend.entity.Product;
import com.grabsy.GrabsyBackend.ervice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
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

    @GetMapping("/find/all/ids")
    public ResponseEntity<CollectionModel<EntityModel<Product>>> findAllById(@RequestParam List<String> id) {
        CollectionModel<EntityModel<Product>> allById = service.findAllById(id);
        return ResponseEntity.ok(allById);
    }

    @PostMapping("/find/all")
    public ResponseEntity<CollectionModel<EntityModel<Product>>> findAllByIdThroughPostBodyRequest(
            @RequestBody List<String> ids) {
        CollectionModel<EntityModel<Product>> allById = service.findAllById(ids);
        return ResponseEntity.ok(allById);
    }

    @PostMapping("/save")
    public ResponseEntity<EntityModel<Product>> save(@RequestBody Product product) {
        EntityModel<Product> savedProduct = service.save(product);
        return ResponseEntity.created(savedProduct.getRequiredLink("self").toUri()).body(savedProduct);
    }

    @PostMapping("/save/all")
    public ResponseEntity<CollectionModel<EntityModel<Product>>> saveAll(@RequestBody List<Product> productListToSave) {
        CollectionModel<EntityModel<Product>> savedProducts = service.saveAll(productListToSave);
        return ResponseEntity.created(savedProducts.getRequiredLink("self").toUri()).body(savedProducts);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<EntityModel<Product>> findByIdAndUpdate(@PathVariable String id,
                                                                  @RequestBody Product productToSave) {
        EntityModel<Product> updatedProductById = service.findByIdAndUpdate(id, productToSave);
        return ResponseEntity.ok(updatedProductById);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable String id) {
        boolean isItemDeleted = service.deleteById(id);
        return isItemDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
