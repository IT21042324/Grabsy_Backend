package com.grabsy.GrabsyBackend.Entity;

import org.springframework.data.annotation.Id;

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
}
