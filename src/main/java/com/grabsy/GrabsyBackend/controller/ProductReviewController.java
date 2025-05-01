package com.grabsy.GrabsyBackend.controller;

import com.grabsy.GrabsyBackend.entity.review.ProductReview;
import com.grabsy.GrabsyBackend.Service.review.ProductReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product/reviews")
public class ProductReviewController {
    private final ProductReviewService service;

    public ProductReviewController(@Autowired ProductReviewService service) {
        this.service = service;
    }

    @GetMapping
    public CollectionModel<EntityModel<ProductReview>> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<ProductReview> findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public EntityModel<ProductReview> save(@Valid @RequestBody ProductReview productReview) {
        return service.save(productReview);
    }

    @GetMapping("/user/{userId}")
    public List<ProductReview> getAllReviewsOfOneUser(@PathVariable String userId) {
        return service.getAllReviewsOfOneUser(userId);
    }

    @GetMapping("/reviewable/{id}")
    public List<ProductReview>
    findReviewsMadeByUserToReviewable(@RequestParam String userId,
                                      @PathVariable(name = "id") String reviewableId,
                                      @RequestParam(required = false) String sortingProperty,
                                      @RequestParam(required = false) Sort.Direction direction) {
        if (sortingProperty == null) return service.findReviewsMadeByUserToReviewable(userId, reviewableId);
        else if (direction == null)
            return service.findReviewsMadeByUserToReviewable(userId, reviewableId, sortingProperty);
        else return service.findReviewsMadeByUserToReviewable(userId, reviewableId, sortingProperty, direction);
    }

    @PatchMapping("/{id}")
    public EntityModel<ProductReview> updateReview(@PathVariable String id, @Valid @RequestBody ProductReview review) {
        return service.updateReview(id, review);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteReview(@PathVariable String id) {
        return service.deleteReview(id);
    }
}
