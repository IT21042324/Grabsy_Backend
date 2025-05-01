package com.grabsy.GrabsyBackend.util;

import com.grabsy.GrabsyBackend.controller.ProductReviewController;
import com.grabsy.GrabsyBackend.controller.StoreReviewController;
import com.grabsy.GrabsyBackend.entity.Product;
import com.grabsy.GrabsyBackend.entity.Store;
import com.grabsy.GrabsyBackend.response.ProductResponse;
import com.grabsy.GrabsyBackend.response.ResponseId;
import com.grabsy.GrabsyBackend.response.StoreResponse;
import org.springframework.hateoas.EntityModel;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class EntityToResponseMapper {
    private static List<EntityModel<ResponseId>> mapReviewsToCollection(Product product) {
        return product.getReviews().stream().map(reviewId -> EntityModel.of(new ResponseId(reviewId),
                linkTo(methodOn(ProductReviewController.class).findById(reviewId)).withSelfRel())).toList();
    }

    private static List<EntityModel<ResponseId>> mapReviewsToCollection(Store store) {
        return store.getReviews().stream().map(reviewId -> EntityModel.of(new ResponseId(reviewId),
                linkTo(methodOn(StoreReviewController.class).findById(reviewId)).withSelfRel())).toList();
    }

    public static ProductResponse productToProductResponseMapper(Product product) {
        ProductResponse productResponse = new ProductResponse(product);
        productResponse.setReviews(mapReviewsToCollection(product));
        return productResponse;
    }

    public static StoreResponse storeToStoreResponseMapper(Store store){
        StoreResponse storeResponse = new StoreResponse(store);
        storeResponse.setReviews(mapReviewsToCollection(store));
        return storeResponse;
    }
}
