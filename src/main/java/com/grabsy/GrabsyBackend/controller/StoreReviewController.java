package com.grabsy.GrabsyBackend.controller;

import com.grabsy.GrabsyBackend.entity.review.ProductReview;
import com.grabsy.GrabsyBackend.entity.review.StoreReview;
import com.grabsy.GrabsyBackend.repository.review.StoreReviewRepository;
import com.grabsy.GrabsyBackend.service.review.ProductReviewService;
import com.grabsy.GrabsyBackend.service.review.StoreReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store/reviews")
public class StoreReviewController {
    private final StoreReviewService service;

    public StoreReviewController(@Autowired StoreReviewService service) {
        this.service = service;
    }

    @GetMapping
    public CollectionModel<EntityModel<StoreReview>> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<StoreReview> findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    public EntityModel<StoreReview> save(@Valid @RequestBody StoreReview storeReview) {
        return service.save(storeReview);
    }

    @GetMapping("/user/{userId}")
    public List<StoreReview> getAllReviewsOfOneUser(@PathVariable String userId) {
        return service.getAllReviewsOfOneUser(userId);
    }

    @GetMapping("/reviewable/{id}")
    public List<StoreReview>
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
    public EntityModel<StoreReview> updateReview(@PathVariable String id, @Valid @RequestBody StoreReview review) {
        return service.updateReview(id, review);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteReview(@PathVariable String id) {
        return service.deleteReview(id);
    }
}
