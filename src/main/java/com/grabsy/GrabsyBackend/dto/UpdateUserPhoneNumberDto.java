package com.grabsy.GrabsyBackend.dto;

import com.grabsy.GrabsyBackend.constant.UserRole;

public class UpdateUserPhoneNumberDto {
    private String userId, newPhoneNumber;
    private UserRole userRole;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNewPhoneNumber() {
        return newPhoneNumber;
    }

    public void setNewPhoneNumber(String newPhoneNumber) {
        this.newPhoneNumber = newPhoneNumber;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
