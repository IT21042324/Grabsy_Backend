package com.grabsy.GrabsyBackend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This class is used to generate the sequence number for the user id rather than using the default MongoDB id.
 */

@Document(collection = "userIdCounter")
public class UserIdCounter {
    @Id
    private String userRole;
    private int counter;

    public UserIdCounter(String userRole, int counter) {
        this.userRole = userRole;
        this.counter = counter;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
