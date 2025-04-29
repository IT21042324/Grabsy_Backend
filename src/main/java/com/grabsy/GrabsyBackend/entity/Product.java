package com.grabsy.GrabsyBackend.entity;
import com.grabsy.GrabsyBackend.entity.review.Reviewable;
import com.grabsy.GrabsyBackend.domain.Review;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "products")
public class Product implements Reviewable {
    @Id
    private String id;
    private String name;
    private String description;
    private Double price;
    private Rendition rendition;
    private Long itemCount;
    private ProductCategory category;
    private String store;
    private Long itemsSold;
    private List<String> reviews;
    private Byte averageRating = 0;
    private Offer offer;

    private Byte Ratings;

    public Product() {
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

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    @Override
    public List<String> getReviews() {
        return this.reviews;
    }

    @Override
    public void setReviews(List<String> reviewList) {
        this.reviews = reviewList;
    }

    @Override
    public Byte getAverageRating(){
        return this.averageRating;
    }

    @Override
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
        return "Product{" +
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
