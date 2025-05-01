package com.grabsy.GrabsyBackend.Service;

import com.grabsy.GrabsyBackend.controller.ProductController;
import com.grabsy.GrabsyBackend.controller.ProductReviewController;
import com.grabsy.GrabsyBackend.entity.Product;
import com.grabsy.GrabsyBackend.entity.review.ProductReview;
import com.grabsy.GrabsyBackend.exception.ProductNotFoundException;
import com.grabsy.GrabsyBackend.exception.ReviewNotFoundException;
import com.grabsy.GrabsyBackend.repository.ProductRepository;
import com.grabsy.GrabsyBackend.response.ProductResponse;
import com.grabsy.GrabsyBackend.Service.review.ProductReviewService;
import com.grabsy.GrabsyBackend.util.BeanReflectionUtil;
import com.grabsy.GrabsyBackend.util.EntityToResponseMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.grabsy.GrabsyBackend.constant.ReviewConstant.REVIEW_DOES_NOT_EXIST_FOR_GIVEN_ID;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductReviewService reviewService;

    public ProductService(ProductRepository productRepository, ProductReviewService reviewService) {
        this.productRepository = productRepository;
        this.reviewService = reviewService;
    }

    public EntityModel<ProductResponse> findById(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        ProductResponse productResponse = EntityToResponseMapper.productToProductResponseMapper(product);

        return EntityModel.of(productResponse, linkTo(methodOn(ProductController.class).findById(id)).withSelfRel(),
                linkTo(methodOn(ProductController.class).findAll()).withRel("products"));
    }

    public CollectionModel<EntityModel<ProductResponse>> findAllById(List<String> ids) {
        List<EntityModel<ProductResponse>> productList = productRepository.findAllById(ids).stream().map(product -> {
            ProductResponse productResponse = EntityToResponseMapper.productToProductResponseMapper(product);
            return EntityModel.of(productResponse,
                    linkTo(methodOn(ProductController.class).findById(product.getId())).withSelfRel());
        }).toList();

        return CollectionModel.of(productList,
                linkTo(methodOn(ProductController.class).findAllById(ids)).withSelfRel());
    }

    public CollectionModel<EntityModel<ProductResponse>> findAll() {
        List<EntityModel<ProductResponse>> productList = productRepository.findAll().stream().map(product -> {
            ProductResponse productResponse = EntityToResponseMapper.productToProductResponseMapper(product);
            return EntityModel.of(productResponse,
                    linkTo(methodOn(ProductController.class).findById(product.getId())).withSelfRel());
        }).toList();

        return CollectionModel.of(productList,
                linkTo(methodOn(ProductController.class).findAll()).withSelfRel());
    }

    public EntityModel<ProductResponse> save(Product product) {
        Product createdProduct = productRepository.save(product);
        ProductResponse productResponse = EntityToResponseMapper.productToProductResponseMapper(createdProduct);

        return EntityModel.of(productResponse, linkTo(methodOn(ProductController.class).findById(productResponse.
                getId())).withSelfRel());
    }

    public CollectionModel<EntityModel<ProductResponse>> saveAll(List<Product> products) {
        List<EntityModel<ProductResponse>> productsCollectionAfterSelfRelation = productRepository.saveAll(products).
                stream().map(product -> {
            ProductResponse productResponse = EntityToResponseMapper.productToProductResponseMapper(product);
            return EntityModel.of(productResponse, linkTo(methodOn(ProductController.class).findById(product.getId())).
                    withSelfRel());}).toList();

        return CollectionModel.of(productsCollectionAfterSelfRelation,
                linkTo(methodOn(ProductController.class).findAllById(products.stream().map(Product::getId).toList())).
                        withSelfRel());
    }

    public EntityModel<ProductResponse> findByIdAndUpdate(String id, Product productToSave) {
        return productRepository.findById(id).map(existingProduct -> {
            // cannot update reviews through this controller function
            productToSave.setReviews(null);

            Product updated = BeanReflectionUtil.copyNonNullFields(existingProduct, productToSave).
                    orElseGet(() -> existingProduct);

            Product saved = productRepository.save(updated);
            ProductResponse productResponse = EntityToResponseMapper.productToProductResponseMapper(saved);

            return EntityModel.of(productResponse, linkTo(methodOn(ProductController.class).findById(id)).
                    withSelfRel());
        }).orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Boolean deleteById(String id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }

        productRepository.deleteById(id);
        return true;
    }

    public CollectionModel<EntityModel<String>> findAllReviewsByProductId(String id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }

        List<EntityModel<String>> list = productRepository.findAllReviewsByProductId(id).getReviews().stream().
                map(reviewId -> EntityModel.of(reviewId, linkTo(methodOn(ProductReviewController.class).
                        findById(reviewId)).withSelfRel())).toList();

        return CollectionModel.of(list, linkTo(methodOn(ProductController.class).findAllReviewsByProductId(id)).
                withSelfRel());
    }

    @Transactional
    public EntityModel<ProductResponse> addReviewToProduct(String productId, ProductReview reviewToAdd) {
        return productRepository.findById(productId).map(product -> {
            ProductReview createdNewReview = reviewService.save(reviewToAdd).getContent();
            product.addReviews(createdNewReview.getId());

            product.updateAverageRating(createdNewReview.getRatings());
            Product savedProduct = productRepository.save(product);

            ProductResponse productResponse = EntityToResponseMapper.productToProductResponseMapper(savedProduct);

            return EntityModel.of(productResponse, linkTo(methodOn(ProductController.class).
                    addReviewToProduct(productId, reviewToAdd)).withSelfRel());
        }).orElseThrow(() -> new ProductNotFoundException(productId));
    }

    @Transactional
    public EntityModel<ProductResponse> deleteReviewFromProduct(String productId, String reviewId) {
        return productRepository.findById(productId).map(existingProduct -> {
            List<String> reviews = existingProduct.getReviews();

            reviewService.deleteReview(reviewId);

            if (reviews == null || !reviews.remove(reviewId)) {
                throw new ReviewNotFoundException(REVIEW_DOES_NOT_EXIST_FOR_GIVEN_ID + reviewId);
            }

            existingProduct.setReviews(reviews);
            Product savedProduct = productRepository.save(existingProduct);

            ProductResponse productResponse = EntityToResponseMapper.productToProductResponseMapper(savedProduct);

            return EntityModel.of(productResponse, linkTo(methodOn(ProductController.class).
                    findById(productId)).withSelfRel());
        }).orElseThrow(() -> new ProductNotFoundException(productId));
    }

    @Transactional
    public EntityModel<ProductReview> updateReviewForProduct(String reviewId, ProductReview productReview) {
        return reviewService.updateReview(reviewId, productReview);
    }

    @Transactional
    public EntityModel<ProductResponse> updateReviewForProduct(String productId, String reviewId, ProductReview productReview) {
        reviewService.updateReview(reviewId, productReview).getContent();

        ProductResponse productResponse = this.findById(productId).getContent();

        return EntityModel.of(productResponse, linkTo(methodOn(ProductController.class).findById(productId)).
                withSelfRel());
    }
}