package com.grabsy.GrabsyBackend.Entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
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

    public String getId(){
        return this.id;
    }
}
