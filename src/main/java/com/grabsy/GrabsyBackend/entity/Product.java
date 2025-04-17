package com.grabsy.GrabsyBackend.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private Double price;
    private Rendition rendition;
    private Long itemCount;
    private ProductCategory category;
    private String origin;
    private Store store;
    private float ratings;
    private Long itemsSold;
    private Offer offer;

    public Product(){}

    public Product(String id){
        this.id = id;
    }

    public Product(String description, Double price, Long itemCount, ProductCategory category, String origin,
                   Store store, float ratings, Long itemsSold) {
        this.description = description;
        this.price = price;
        this.itemCount = itemCount;
        this.category = category;
        this.origin = origin;
        this.store = store;
        this.ratings = ratings;
        this.itemsSold = itemsSold;
    }

    public Product(String description, Double price, Long itemCount, ProductCategory category, String origin,
                   Store store, float ratings, Long itemsSold, Offer offer) {
        this.description = description;
        this.price = price;
        this.itemCount = itemCount;
        this.category = category;
        this.origin = origin;
        this.store = store;
        this.ratings = ratings;
        this.itemsSold = itemsSold;
        this.offer = offer;
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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
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

    public float getRatings() {
        return ratings;
    }

    public void setRatings(float ratings) {
        this.ratings = ratings;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Float.compare(ratings, product.ratings) == 0
                && Objects.equals(name, product.name)
                && Objects.equals(description, product.description) && Objects.equals(price, product.price)
                && Objects.equals(rendition, product.rendition) && Objects.equals(itemCount, product.itemCount)
                && Objects.equals(category, product.category) && Objects.equals(store, product.store)
                && Objects.equals(itemsSold, product.itemsSold) && Objects.equals(offer, product.offer);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", rendition=" + rendition +
                ", itemCount=" + itemCount +
                ", category=" + category +
                ", origin='" + origin + '\'' +
                ", store=" + store +
                ", ratings=" + ratings +
                ", itemsSold=" + itemsSold +
                ", offer=" + offer +
                '}';
    }
}
