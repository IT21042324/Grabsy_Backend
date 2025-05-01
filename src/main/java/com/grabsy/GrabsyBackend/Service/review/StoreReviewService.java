package com.grabsy.GrabsyBackend.Service.review;

import com.grabsy.GrabsyBackend.controller.ProductReviewController;
import com.grabsy.GrabsyBackend.controller.StoreReviewController;
import com.grabsy.GrabsyBackend.entity.review.StoreReview;
import com.grabsy.GrabsyBackend.exception.ReviewNotFoundException;
import com.grabsy.GrabsyBackend.repository.review.StoreReviewRepository;
import com.grabsy.GrabsyBackend.util.BeanReflectionUtil;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.grabsy.GrabsyBackend.constant.ReviewConstant.*;
import static com.grabsy.GrabsyBackend.constant.ReviewConstant.DEFAULT_SORT_FIELD;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class StoreReviewService implements ReviewService<StoreReview> {
    private final StoreReviewRepository storeReviewRepository;
    public StoreReviewService(StoreReviewRepository reviewRepository) {
        storeReviewRepository = reviewRepository;
    }

    public EntityModel<StoreReview> findById(String id){
        StoreReview review = storeReviewRepository.findById(id).orElseThrow(() ->
                new ReviewNotFoundException(REVIEW_NOT_FOUND_WITH_ID));

        return EntityModel.of(review, linkTo(methodOn(StoreReviewController.class).findById(id)).withSelfRel());
    }

    public CollectionModel<EntityModel<StoreReview>> findAll(){
        List<EntityModel<StoreReview>> allReviews = storeReviewRepository.findAll().stream().map(storeReview ->
                EntityModel.of(storeReview, linkTo(methodOn(ProductReviewController.class).
                        findById(storeReview.getId())).withSelfRel())).toList();

        return CollectionModel.of(allReviews, linkTo(methodOn(ProductReviewController.class).findAll()).withSelfRel());
    }

    public EntityModel<StoreReview> save(StoreReview review) {
        StoreReview newReview = storeReviewRepository.save(review);
        return EntityModel.of(review, linkTo(methodOn(StoreReviewController.class).findById(newReview.getId())).withSelfRel());
    }

    @Override
    public List<StoreReview> getAllReviewsOfOneUser(String userId) {
        List<StoreReview> storeReviewsMadeByUser = storeReviewRepository.findByUserId(userId);
        if(storeReviewsMadeByUser.isEmpty()){
            throw new ReviewNotFoundException(NO_REVIEWS_FOR_USER);
        }
        return storeReviewsMadeByUser;
    }

    @Override
    public List<StoreReview> findReviewsMadeByUserToReviewable(String userId, String reviewableId) {
        Sort sort = Sort.by(DEFAULT_SORT_DIRECTION, DEFAULT_SORT_FIELD);

        List<StoreReview> storeReviewsMadeByUser =  storeReviewRepository.findReviewsMadeByUserToStore(userId,
                reviewableId, sort);

        if(storeReviewsMadeByUser.isEmpty())
            throw new ReviewNotFoundException(NO_REVIEWS_FOR_USER);

        return storeReviewsMadeByUser;
    }

    @Override
    public List<StoreReview> findReviewsMadeByUserToReviewable(String userId, String reviewableId, String
            sortingProperty) {
        Sort sort = Sort.by(DEFAULT_SORT_DIRECTION, sortingProperty);

        List<StoreReview> storeReviewsMadeByUser =  storeReviewRepository.findReviewsMadeByUserToStore(userId,
                reviewableId, sort);

        if(storeReviewsMadeByUser.isEmpty()){
            throw new ReviewNotFoundException(NO_REVIEWS_FOR_USER);
        }

        return storeReviewsMadeByUser;
    }

    @Override
    public List<StoreReview> findReviewsMadeByUserToReviewable(String userId, String reviewableId,
                                                               String sortingProperty, Sort.Direction direction) {
        Sort sort = Sort.by(direction, sortingProperty);

        List<StoreReview> storeReviewsMadeByUser =  storeReviewRepository.findReviewsMadeByUserToStore(userId,
                reviewableId, sort);

        if(storeReviewsMadeByUser.isEmpty()){
            throw new ReviewNotFoundException(NO_REVIEWS_FOR_USER);
        }

        return storeReviewsMadeByUser;
    }

    @Override
    public EntityModel<StoreReview> updateReview(String id, StoreReview review) {
        return storeReviewRepository.findById(id).map(storeReview-> {
            StoreReview finalProductToUpdate = BeanReflectionUtil.copyNonNullFields(storeReview, review).
                    orElseThrow();

            StoreReview save = storeReviewRepository.save(finalProductToUpdate);
            return EntityModel.of(save, linkTo(methodOn(ProductReviewController.class).findById(save.getId())).withSelfRel());
        }).orElseThrow(() -> new ReviewNotFoundException(REVIEW_NOT_FOUND_WITH_ID + id));
    }

    @Override
    public Boolean deleteReview(String id) {
        if(!storeReviewRepository.existsById(id))
            throw new ReviewNotFoundException(REVIEW_NOT_FOUND_WITH_ID + id);

        storeReviewRepository.deleteById(id);
        return true;
    }
}
