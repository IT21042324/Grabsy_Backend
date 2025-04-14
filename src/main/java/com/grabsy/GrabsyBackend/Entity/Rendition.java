package com.grabsy.GrabsyBackend.Entity;

import org.springframework.data.annotation.Id;

public class Rendition {
    @Id
    private String id;

    public Rendition() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
