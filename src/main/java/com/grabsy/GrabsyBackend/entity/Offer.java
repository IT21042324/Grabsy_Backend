package com.grabsy.GrabsyBackend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "offers")
public class Offer {
    @Id
    private String id;

    public Offer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id='" + id + '\'' +
                '}';
    }
}
