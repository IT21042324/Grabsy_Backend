package com.grabsy.GrabsyBackend.Entity;

import org.springframework.data.annotation.Id;

public class Store {
    @Id
    private String id;

    public Store() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
