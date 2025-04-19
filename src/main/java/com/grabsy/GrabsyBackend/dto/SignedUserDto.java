package com.grabsy.GrabsyBackend.dto;

/**
 * This class is a Data Transfer Object (DTO) for signed users.
 */

public class SignedUserDto {
    private String name, email, password;

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
