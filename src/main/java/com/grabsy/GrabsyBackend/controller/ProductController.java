package com.grabsy.GrabsyBackend.controller;

import com.grabsy.GrabsyBackend.entity.Product;
import com.grabsy.GrabsyBackend.entity.review.ProductReview;
import com.grabsy.GrabsyBackend.response.ProductResponse;
import com.grabsy.GrabsyBackend.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ProductResponse>> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<ProductResponse>>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/find/all/ids")
    public ResponseEntity<CollectionModel<EntityModel<ProductResponse>>> findAllById(@RequestParam List<String> id) {
        CollectionModel<EntityModel<ProductResponse>> allById = service.findAllById(id);
        return ResponseEntity.ok(allById);
    }

    @PostMapping("/find/all")
    public ResponseEntity<CollectionModel<EntityModel<ProductResponse>>> findAllByIdThroughPostBodyRequest(
            @RequestBody List<String> ids) {
        CollectionModel<EntityModel<ProductResponse>> allById = service.findAllById(ids);
        return ResponseEntity.ok(allById);
    }

    @PostMapping("/save")
    public ResponseEntity<EntityModel<ProductResponse>> save(@RequestBody Product product) {
        EntityModel<ProductResponse> savedProduct = service.save(product);
        return ResponseEntity.created(savedProduct.getRequiredLink("self").toUri()).body(savedProduct);
    }

    @PostMapping("/save/all")
    public ResponseEntity<CollectionModel<EntityModel<ProductResponse>>> saveAll(@RequestBody List<Product> productListToSave) {
        CollectionModel<EntityModel<ProductResponse>> savedProducts = service.saveAll(productListToSave);
        return ResponseEntity.created(savedProducts.getRequiredLink("self").toUri()).body(savedProducts);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<EntityModel<ProductResponse>> findByIdAndUpdate(@PathVariable String id,
                                                                  @RequestBody Product productToSave) {
        EntityModel<ProductResponse> updatedProductById = service.findByIdAndUpdate(id, productToSave);
        return ResponseEntity.ok(updatedProductById);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable String id) {
        boolean isItemDeleted = service.deleteById(id);
        return isItemDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/reviews/{id}")
    public CollectionModel<EntityModel<String>> findAllReviewsByProductId(@PathVariable String id) {
        return service.findAllReviewsByProductId(id);
    }

    @PostMapping("/reviews/{id}")
    public ResponseEntity<EntityModel<ProductResponse>>
    addReviewToProduct(@PathVariable String id, @Valid @RequestBody ProductReview reviewToAdd) {
        return ResponseEntity.ok(service.addReviewToProduct(id, reviewToAdd));
    }

    @DeleteMapping("/{productId}/reviews/{reviewId}")
    public EntityModel<ProductResponse> deleteReviewFromProduct(@PathVariable String productId,
                                                                @PathVariable String reviewId) {
        return service.deleteReviewFromProduct(productId, reviewId);
    }

    @PatchMapping("/{productId}/reviews/update/{reviewId}")
    public EntityModel<ProductResponse> updateReviewForProduct(@PathVariable String productId,
                                                               @PathVariable String reviewId,
                                                               @Valid @RequestBody ProductReview review) {
        return service.updateReviewForProduct(productId, reviewId, review);
    }

    @PatchMapping("/reviews/update/{reviewId}")
    public EntityModel<ProductReview> updateReviewForProduct(@PathVariable String reviewId,
                                                             @Valid @RequestBody ProductReview review) {
        return service.updateReviewForProduct(reviewId, review);
    }
}