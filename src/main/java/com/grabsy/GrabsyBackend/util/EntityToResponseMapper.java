package com.grabsy.GrabsyBackend.util;

import com.grabsy.GrabsyBackend.controller.ProductReviewController;
import com.grabsy.GrabsyBackend.entity.Product;
import com.grabsy.GrabsyBackend.response.ProductResponse;
import com.grabsy.GrabsyBackend.response.ResponseId;
import org.springframework.hateoas.EntityModel;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class EntityToResponseMapper {
    private static List<EntityModel<ResponseId>> mapReviewsToCollection(Product product) {
        return product.getReviews().stream().map(reviewId -> EntityModel.of(new ResponseId(reviewId),
                linkTo(methodOn(ProductReviewController.class).findById(reviewId)).withSelfRel())).toList();
    }

    public static ProductResponse productToProductResponseMapper(Product product) {
        ProductResponse productResponse = new ProductResponse(product);
        productResponse.setReviews(mapReviewsToCollection(product));
        return productResponse;
    }
}
