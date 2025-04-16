package com.grabsy.GrabsyBackend.Service.Review;
import com.grabsy.GrabsyBackend.Entity.Review.ProductReview;
import com.grabsy.GrabsyBackend.Repository.Review.ProductReviewRepository;
import com.grabsy.GrabsyBackend.Util.BeanReflectionUtil;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
            throw new RuntimeException("No Reviews From The Given User");
        }
        return productReviewsMadeByUser;
    }

    @Override
    public List<ProductReview> findReviewsMadeByUserToReviewable(String userId, String reviewableId) {
        List<ProductReview> productReviewsMadeByUser =  productReviewRepository.findReviewsMadeByUserToProduct(userId,
                reviewableId);

        if(productReviewsMadeByUser.isEmpty())
            throw new RuntimeException("No Reviews From The Given User");

        return productReviewsMadeByUser;
    }

    @Override
    public List<ProductReview> findReviewsMadeByUserToReviewable(String userId, String reviewableId,
                                                                 String sortingProperty) {
        Sort sort = Sort.by(Sort.Direction.ASC, sortingProperty);

        List<ProductReview> productReviewsMadeByUser =  productReviewRepository.findReviewsMadeByUserToProduct(userId,
                reviewableId, sort);

        if(productReviewsMadeByUser.isEmpty()){
            throw new RuntimeException("No Reviews From The Given User");
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
            throw new RuntimeException("No Reviews From The Given User");
        }

        return productReviewsMadeByUser;
    }

    @Override
    public ProductReview updateReview(String id, ProductReview review) {
        return productReviewRepository.findById(id).map(productReview-> {
            ProductReview finalProductToUpdate = BeanReflectionUtil.copyNonNullFields(productReview, review).
                    orElseThrow(() -> new RuntimeException("Review not found with id: " + id));

            return productReviewRepository.save(finalProductToUpdate);
        }).orElseThrow(RuntimeException::new);
    }

    @Override
    public Boolean deleteReview(String id) {
        if(!productReviewRepository.existsById(id))
            throw new RuntimeException("Review not found with id: " + id);

        productReviewRepository.deleteById(id);
        return true;
    }
}
