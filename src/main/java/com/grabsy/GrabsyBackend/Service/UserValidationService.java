package com.grabsy.GrabsyBackend.service;

import com.grabsy.GrabsyBackend.constant.UserRole;
import com.grabsy.GrabsyBackend.repository.AdminRepository;
import com.grabsy.GrabsyBackend.repository.CustomerRepository;
import com.grabsy.GrabsyBackend.repository.SellerRepository;
import org.springframework.stereotype.Service;

/**
 * This class is a service for user validation, it contains methods to validate user input.
 */

@Service
public class UserValidationService {
    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;
    private final AdminRepository adminRepository;

    public UserValidationService(CustomerRepository customerRepository, SellerRepository sellerRepository, AdminRepository adminRepository) {
        this.customerRepository = customerRepository;
        this.sellerRepository = sellerRepository;
        this.adminRepository = adminRepository;
    }

    /**
     * This method checks if the phone number is valid.
     * Ensures it's not null and contains exactly 10 digits.
     * @param phoneNumber
     */
    public void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches("^\\d{10}$")) {
            throw new IllegalArgumentException("Phone number must be 10 digits");
        }
    }

    /**
     * This method checks if the password is valid.
     * Ensures it's not null, contains at least 8 characters, and includes at least one uppercase letter, one lowercase letter, one number, and one special character.
     * @param password
     */
    public void validatePassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }

        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters");
        }

        if (!password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Password must contain an uppercase letter");
        }

        if (!password.matches(".*[a-z].*")) {
            throw new IllegalArgumentException("Password must contain a lowercase letter");
        }

        if (!password.matches(".*[0-9].*")) {
            throw new IllegalArgumentException("Password must contain a number");
        }

        if (!password.matches(".*[@#$%^&+=].*")) {
            throw new IllegalArgumentException("Password must contain a special character");
        }
    }

    /**
     * This method checks if the email is valid.
     * @param email
     * @return true if the email is valid, false otherwise
     */
    public boolean isValidEmailFormat(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    /**
     * This method checks if the email is unique.
     * @param email
     * @return true if the email is unique, false otherwise
     */
    public boolean isEmailExist(UserRole userRole, String email) {
        return switch (userRole) {
            case CUSTOMER -> customerRepository.existsCustomerByEmail(email);
            case SELLER -> sellerRepository.existsSellerByEmail(email);
            case ADMIN -> adminRepository.existsAdminByEmail(email);
            default -> throw new IllegalArgumentException("Invalid user role to check email uniqueness");
        };
    }

    /**
     * This method checks if the email is valid.
     * Whether it starts with one or more letters, numbers, or special characters, followed by an @ symbol and a domain.
     *
     * @param userRole
     * @param email
     */
    // TODO : Implement logic to check if it's an actual email, not just whether the syntax is correct. Let the user know what the issue is, like what is missing
    public void validateEmail(UserRole userRole, String email) {
        if (!isValidEmailFormat(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }

        if (isEmailExist(userRole, email)) {
            throw new IllegalArgumentException("Email already exists");
        }
    }
}
