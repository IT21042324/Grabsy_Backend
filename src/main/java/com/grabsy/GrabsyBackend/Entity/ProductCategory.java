package com.grabsy.GrabsyBackend.Entity;

import org.springframework.data.annotation.Id;

public class ProductCategory {
    @Id
    private String id;
    public ProductCategory(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
