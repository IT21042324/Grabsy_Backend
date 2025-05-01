package com.grabsy.GrabsyBackend.service.review;
import com.grabsy.GrabsyBackend.controller.ProductReviewController;
import com.grabsy.GrabsyBackend.entity.review.ProductReview;
import com.grabsy.GrabsyBackend.exception.ReviewNotFoundException;
import com.grabsy.GrabsyBackend.repository.review.ProductReviewRepository;
import com.grabsy.GrabsyBackend.util.BeanReflectionUtil;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.grabsy.GrabsyBackend.constant.ReviewConstant.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import java.util.List;


@Service
public class ProductReviewService implements ReviewService<ProductReview> {
    ProductReviewRepository productReviewRepository;
    public ProductReviewService(ProductReviewRepository productReviewRepository) {
        this.productReviewRepository = productReviewRepository;
    }

    public EntityModel<ProductReview> findById(String id){
        ProductReview review = productReviewRepository.findById(id).orElseThrow(() ->
                new ReviewNotFoundException(REVIEW_NOT_FOUND_WITH_ID));

        return EntityModel.of(review, linkTo(methodOn(ProductReviewController.class).findById(id)).withSelfRel());
    }

    public CollectionModel<EntityModel<ProductReview>> findAll(){
        List<EntityModel<ProductReview>> allReviews = productReviewRepository.findAll().stream().map(productReview ->
                EntityModel.of(productReview, linkTo(methodOn(ProductReviewController.class).
                        findById(productReview.getId())).withSelfRel())).toList();

        return CollectionModel.of(allReviews, linkTo(methodOn(ProductReviewController.class).findAll()).withSelfRel());
    }

    @Transactional
    public EntityModel<ProductReview> save(ProductReview review) {
        ProductReview newReview = productReviewRepository.save(review);
        return EntityModel.of(review, linkTo(methodOn(ProductReviewController.class).findById(newReview.getId())).withSelfRel());
    }

    @Override
    public List<ProductReview> getAllReviewsOfOneUser(String userId) {
        List<ProductReview> productReviewsMadeByUser = productReviewRepository.findByUserId(userId);
        if(productReviewsMadeByUser.isEmpty()){
            throw new ReviewNotFoundException(NO_REVIEWS_FOR_USER);
        }
        return productReviewsMadeByUser;
    }

    @Override
    public List<ProductReview> findReviewsMadeByUserToReviewable(String userId, String reviewableId) {
        Sort sort = Sort.by(DEFAULT_SORT_DIRECTION, DEFAULT_SORT_FIELD);

        List<ProductReview> productReviewsMadeByUser =  productReviewRepository.findReviewsMadeByUserToProduct(userId,
                reviewableId, sort);

        if(productReviewsMadeByUser.isEmpty())
            throw new ReviewNotFoundException(NO_REVIEWS_FOR_USER);

        return productReviewsMadeByUser;
    }

    @Override
    public List<ProductReview> findReviewsMadeByUserToReviewable(String userId, String reviewableId,
                                                                 String sortingProperty) {
        Sort sort = Sort.by(DEFAULT_SORT_DIRECTION, sortingProperty);

        List<ProductReview> productReviewsMadeByUser =  productReviewRepository.findReviewsMadeByUserToProduct(userId,
                reviewableId, sort);

        if(productReviewsMadeByUser.isEmpty()){
            throw new ReviewNotFoundException(NO_REVIEWS_FOR_USER);
        }

        return productReviewsMadeByUser;
    }

    @Override
    public List<ProductReview> findReviewsMadeByUserToReviewable(String userId, String reviewableId,
                                                                 String sortingProperty, Sort.Direction direction) {
        Sort sort = Sort.by(direction, sortingProperty);

        List<ProductReview> productReviewsMadeByUser =  productReviewRepository.findReviewsMadeByUserToProduct(userId,
                reviewableId, sort);

        if(productReviewsMadeByUser.isEmpty()){
            throw new ReviewNotFoundException(NO_REVIEWS_FOR_USER);
        }

        return productReviewsMadeByUser;
    }

    @Override
    @Transactional
    public EntityModel<ProductReview> updateReview(String id, ProductReview review) {
        return productReviewRepository.findById(id).map(productReview-> {
            ProductReview finalProductToUpdate = BeanReflectionUtil.copyNonNullFields(productReview, review).
                    orElseThrow();

            ProductReview save = productReviewRepository.save(finalProductToUpdate);
            return EntityModel.of(save, linkTo(methodOn(ProductReviewController.class).findById(save.getId())).withSelfRel());
        }).orElseThrow(() -> new ReviewNotFoundException(REVIEW_NOT_FOUND_WITH_ID + id));
    }

    @Override
    @Transactional
    public Boolean deleteReview(String id) {
        if(!productReviewRepository.existsById(id))
            throw new ReviewNotFoundException(REVIEW_NOT_FOUND_WITH_ID + id);

        productReviewRepository.deleteById(id);
        return true;
    }
}
