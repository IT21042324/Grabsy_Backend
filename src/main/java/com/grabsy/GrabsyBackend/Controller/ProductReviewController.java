package com.grabsy.GrabsyBackend.Controller;

import com.grabsy.GrabsyBackend.Entity.Review.ProductReview;
import com.grabsy.GrabsyBackend.Service.Review.ProductReviewService;
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
    public ProductReview save(ProductReview productReview) {
        return service.save(productReview);
    }

    @GetMapping
    public List<ProductReview> getAllReviewsOfOneUser(@RequestParam String userId) {
        return service.getAllReviewsOfOneUser(userId);
    }

    @GetMapping("/{id}")
    public List<ProductReview> findReviewsMadeByUserToReviewable(@RequestParam String userId, @PathVariable String id) {
        return service.findReviewsMadeByUserToReviewable(userId, id);
    }

    @GetMapping("/{id}")
    public List<ProductReview> findReviewsMadeByUserToReviewable(@RequestParam String userId,
                                                                 @PathVariable(name = "id") String reviewableId,
                                                                 @RequestParam String sortingProperty) {
        return service.findReviewsMadeByUserToReviewable(userId, reviewableId, sortingProperty);
    }

    @GetMapping("/{id}")
    public List<ProductReview> findReviewsMadeByUserToReviewable(@RequestParam String userId,
                                                                 @PathVariable(name = "id") String reviewableId,
                                                                 @RequestParam String sortingProperty,
                                                                 @RequestParam Sort.Direction direction){
        return service.findReviewsMadeByUserToReviewable(userId, reviewableId, sortingProperty,direction);
    }

    @PatchMapping("/{id}")
    public ProductReview updateReview(@PathVariable String id, @RequestBody ProductReview review) {
        return service.updateReview(id, review);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteReview(@PathVariable String id){
        return service.deleteReview(id);
    }
}
