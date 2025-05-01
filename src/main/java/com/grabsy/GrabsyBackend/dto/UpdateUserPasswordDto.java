package com.grabsy.GrabsyBackend.dto;

import com.grabsy.GrabsyBackend.constant.UserRole;

public class UpdateUserPasswordDto {
    private String userId, oldPassword, newPassword, retypedNewPassword;
    private UserRole userRole;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRetypedNewPassword() {
        return retypedNewPassword;
    }

    public void setRetypedNewPassword(String retypedNewPassword) {
        this.retypedNewPassword = retypedNewPassword;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
