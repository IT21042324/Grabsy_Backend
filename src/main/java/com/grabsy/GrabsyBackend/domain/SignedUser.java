package com.grabsy.GrabsyBackend.domain;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

/**
 * The SignedUser domain object represent the structure of a signed user in the system.
 */

public abstract class SignedUser {
    // attributes
    @Id
    // TODO : Change userID to string, and use custom id generator class to create userID based on whether the person is customer, seller, or admin
    protected String userId;
    protected String userRole, name, email, passwordHash, phoneNumber;
    protected LocalDateTime registrationDate;

    // constructors
    public SignedUser(){}

    public SignedUser(String userId, String userRole, String name, String email, String passwordHash, String phoneNumber, LocalDateTime registrationDate) {
        this.userId = userId;
        this.userRole = userRole;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.phoneNumber = phoneNumber;
        this.registrationDate = registrationDate;
    }

    // getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
