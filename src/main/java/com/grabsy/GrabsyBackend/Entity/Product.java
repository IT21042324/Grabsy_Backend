package com.grabsy.GrabsyBackend.Entity;


import org.springframework.data.annotation.Id;

public class Product {
    @Id
    private String Id;
    private String name;
    private String description;
    private Double price;
    private Rendition rendition;
    private Long itemCount;
    private ProductCategory category;
    private Store store;
    private float ratings;
    private Long itemsSold;
    private Offer offer;

    public Product(String description, Double price, Long itemCount, ProductCategory category, Store store, float ratings,
                   Long itemsSold) {
        this.description = description;
        this.price = price;
        this.itemCount = itemCount;
        this.category = category;
        this.store = store;
        this.ratings = ratings;
        this.itemsSold = itemsSold;
    }
    public Product(String description, Double price, Long itemCount, ProductCategory category, Store store, float ratings,
                   Long itemsSold, Offer offer) {
        this.description = description;
        this.price = price;
        this.itemCount = itemCount;
        this.category = category;
        this.store = store;
        this.ratings = ratings;
        this.itemsSold = itemsSold;
        this.offer = offer;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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
}
