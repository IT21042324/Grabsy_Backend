package com.grabsy.GrabsyBackend.service.user;

import com.grabsy.GrabsyBackend.exception.user.*;
import com.grabsy.GrabsyBackend.repository.user.EmailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * This class is a service for user validation, it contains methods to validate user input.
 */

@Service
public class UserValidationService {
    private static final Logger log = LoggerFactory.getLogger(UserValidationService.class);

    public UserValidationService() {
    }

    public void validateName(String name){
        if (name == null || name.trim().isEmpty()){
            log.error("Name cannot be null");
            throw new InvalidNameException("Name cannot be null");
        }

        // check if name has only letters
        if (!name.matches("^[a-zA-Z\\s]+$")){
            log.error("Name can only contain letters");
            throw new InvalidNameException("Name can only contain letters");
        }
    }

    /**
     * This method checks if the phone number is valid.
     * Ensures it's not null and contains exactly 10 digits.
     * @param phoneNumber The phone number to validate
     */
    public void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            log.error("Phone number cannot be null");
            throw new InvalidPhoneNumberException("Phone number cannot be null");
        }

        if (!phoneNumber.matches("^\\d{10}$")) {
            log.error("Phone number must be 10 digits");
            throw new InvalidPhoneNumberException("Phone number must be 10 digits");
        }
    }

    /**
     * This method checks if the password is valid.
     * Ensures it's not null, contains at least 8 characters, and includes at least one uppercase letter, one lowercase letter, one number, and one special character.
     * @param password The password to validate
     */
    public void validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            log.error("Password cannot be null");
            throw new InvalidPasswordException("Password cannot be null");
        }

        if (password.length() < 8) {
            log.error("Password must be at least 8 characters");
            throw new InvalidPasswordException("Password must be at least 8 characters");
        }

        if (!password.matches(".*[A-Z].*")) {
            log.error("Password must contain an uppercase letter");
            throw new InvalidPasswordException("Password must contain an uppercase letter");
        }

        if (!password.matches(".*[a-z].*")) {
            log.error("Password must contain a lowercase letter");
            throw new InvalidPasswordException("Password must contain a lowercase letter");
        }

        if (!password.matches(".*[0-9].*")) {
            log.error("Password must contain a number");
            throw new InvalidPasswordException("Password must contain a number");
        }

        if (!password.matches(".*[@#$%^&+=].*")) {
            log.error("Password must contain a special character");
            throw new InvalidPasswordException("Password must contain a special character");
        }
    }

    private void isValidEmailFormat(String email) {
        if(email == null || email.trim().isEmpty()){
            log.error("Email cannot be null");
            throw new InvalidEmailException("Email cannot be null");
        }

        if(!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")){
            log.error("Invalid email format");
            throw new InvalidEmailException("Invalid email format");
        }

    }

    private <T extends EmailRepository> void isEmailExist(T repository, String email) {
        try {
            if(repository.existsByEmail(email)){
                log.error("Email already exists");
                throw new InvalidEmailException("Email already exists");
            }
        } catch (DataAccessException e){
            log.error("Error checking email existence", e);
            throw new UserFetchException("Error checking email existence", e);
        }
    }

    /**
     * This method checks if the email is valid.
     * Whether it starts with one or more letters, numbers, or special characters, followed by an @ symbol and a domain.
     *
     * @param repository The repository to check for email existence
     * @param email The email to validate
     */
    // TODO : Implement logic to check if it's an actual email, not just whether the syntax is correct. Let the user know what the issue is, like what is missing
    public <T extends EmailRepository> void validateEmail(T repository, String email) {
        isValidEmailFormat(email);
        isEmailExist(repository, email);
    }
}
