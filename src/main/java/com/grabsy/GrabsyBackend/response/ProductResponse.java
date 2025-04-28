package com.grabsy.GrabsyBackend.response;

import com.grabsy.GrabsyBackend.entity.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.util.List;

public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private Double price;
    private Rendition rendition;
    private Long itemCount;
    private ProductCategory category;
    private Store store;
    private Long itemsSold;

    private List<EntityModel<ResponseId>> reviews;
    private Byte averageRating = 0;
    private Offer offer;

    private Byte Ratings;

    public ProductResponse() {
    }
    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.rendition = product.getRendition();
        this.itemCount = product.getItemCount();
        this.category = product.getCategory();
        this.store = product.getStore();
        this.itemsSold = product.getItemsSold();
        this.averageRating = product.getAverageRating();
        this.offer = product.getOffer();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Rendition getRendition() {
        return rendition;
    }

    public void setRendition(Rendition rendition) {
        this.rendition = rendition;
    }

    public Long getItemCount() {
        return itemCount;
    }

    public void setItemCount(Long itemCount) {
        this.itemCount = itemCount;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<EntityModel<ResponseId>> getReviews() {
        return this.reviews;
    }

    public void setReviews(List<EntityModel<ResponseId>> reviewList) {
        this.reviews = reviewList;
    }

    public Byte getAverageRating(){
        return this.averageRating;
    }

    public void setAverageRating(byte averageRating) {
        this.averageRating = averageRating;
    }

    public Long getItemsSold() {
        return itemsSold;
    }

    public void setItemsSold(Long itemsSold) {
        this.itemsSold = itemsSold;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", rendition=" + rendition +
                ", itemCount=" + itemCount +
                ", category=" + category +
                ", store=" + store +
                ", itemsSold=" + itemsSold +
                ", reviews=" + reviews +
                ", offer=" + offer +
                ", Ratings=" + Ratings +
                '}';
    }
}
