package com.grabsy.GrabsyBackend.service.review;
import com.grabsy.GrabsyBackend.entity.review.ProductReview;
import com.grabsy.GrabsyBackend.exception.ReviewNotFoundException;
import com.grabsy.GrabsyBackend.repository.review.ProductReviewRepository;
import com.grabsy.GrabsyBackend.util.BeanReflectionUtil;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import static com.grabsy.GrabsyBackend.contant.ReviewConstant.*;


import java.util.List;


@Service
public class ProductReviewService implements ReviewService<ProductReview> {
    ProductReviewRepository productReviewRepository;
    public ProductReviewService(ProductReviewRepository productReviewRepository) {
        this.productReviewRepository = productReviewRepository;
    }

    public ProductReview save(ProductReview review) {
        return productReviewRepository.save(review);
    }

    public List<ProductReview> findAll(){
        return productReviewRepository.findAll();
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
    public ProductReview updateReview(String id, ProductReview review) {
        return productReviewRepository.findById(id).map(productReview-> {
            ProductReview finalProductToUpdate = BeanReflectionUtil.copyNonNullFields(productReview, review).
                    orElseThrow();

            return productReviewRepository.save(finalProductToUpdate);
        }).orElseThrow(() -> new ReviewNotFoundException(REVIEW_NOT_FOUND_WITH_ID + id));
    }

    @Override
    public Boolean deleteReview(String id) {
        if(!productReviewRepository.existsById(id))
            throw new ReviewNotFoundException(REVIEW_NOT_FOUND_WITH_ID + id);

        productReviewRepository.deleteById(id);
        return true;
    }
}
