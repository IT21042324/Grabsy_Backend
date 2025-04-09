package com.grabsy.GrabsyBackend.domain;

import java.util.Date;

/**
 * The SignedUser domain object represent the structure of a signed user in the system.
 */

public abstract class SignedUser {
    // attributes
    protected String userId, userRole, name, email, passwordHash, phoneNumber;
    protected Date registrationDate;

    // constructor
    public SignedUser(String userId, String userRole, String name, String email, String passwordHash, String phoneNumber, Date registrationDate) {
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
