package com.grabsy.GrabsyBackend.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document(collection= "productcategories")
public class ProductCategory {
    @Id
    private String id;
    private String name;
    private String description;
    private List<String> products;
    private CategoryType categoryType;

    public enum CategoryType{
        HOME_APPLIANCES, HOME_AND_GARDEN, FURNITURE,CLOTHING, ACCESSORIES, SHOES, BAGS, COSTUMES, CONSUMER_ELECTRONICS,
        TOYS_AND_GAMES, JEWELLERY
    }

    public ProductCategory(){}

    public String getId() {
        return id;
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

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategory that = (ProductCategory) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(products, that.products) && categoryType == that.categoryType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, products, categoryType);
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", products=" + products +
                ", categoryType=" + categoryType +
                '}';
    }
}
