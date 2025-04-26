package com.grabsy.GrabsyBackend.controller;

import com.grabsy.GrabsyBackend.entity.review.ProductReview;
import com.grabsy.GrabsyBackend.service.review.ProductReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products/reviews")
public class ProductReviewController {
    private final ProductReviewService service;

    public ProductReviewController(@Autowired ProductReviewService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductReview> findAll() {
        return service.findAll();
    }

    @PostMapping
    public ProductReview save(@Valid @RequestBody ProductReview productReview) {
        return service.save(productReview);
    }

    @GetMapping("/{id}")
    public List<ProductReview> getAllReviewsOfOneUser(@PathVariable(name = "id") String userId) {
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
    public ProductReview updateReview(@PathVariable String id, @Valid @RequestBody ProductReview review) {
        return service.updateReview(id, review);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteReview(@PathVariable String id) {
        return service.deleteReview(id);
    }
}
