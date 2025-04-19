package com.grabsy.GrabsyBackend.service;

import com.grabsy.GrabsyBackend.constant.UserRole;
import com.grabsy.GrabsyBackend.dto.SignedUserDto;
import com.grabsy.GrabsyBackend.entity.users.Customer;
import com.grabsy.GrabsyBackend.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDateTime;

/**
 * This class is a service for the Customer entity, it contains business logic related to customers.
 */

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UserIdGeneratorService userIdGeneratorService;

    // constructor
    public CustomerService(CustomerRepository customerRepository, UserIdGeneratorService userIdGeneratorService) {
        this.customerRepository = customerRepository;
        this.userIdGeneratorService = userIdGeneratorService;
    }

    // TODO : Doesn't this need exception handling? What about ID? What about other variables?
    public Customer registerCustomer(SignedUserDto userDto) throws IllegalAccessException {
        // validate the userDto
        if (!isValidEmail(userDto.getEmail())){
            throw new IllegalAccessException("Invalid email format"); // is this the correct exception to throw ?
        }

        // TODO : Let user know what exactly is the issue, whether it's not enough characters and so on
        if (!isValidPassword(userDto.getPassword())){
            throw new IllegalArgumentException("Password does not meet security criteria");
        }

        if(!isValidPhoneNumber(userDto.getPhoneNumber())){
            throw new IllegalArgumentException("Invalid phone number");
        }

        if (!isValidShippingAddress(userDto.getShippingAddress())){
            throw new IllegalArgumentException("Invalid shipping address");
        }

        if (isValidEmail(userDto.getEmail())){
            throw new IllegalArgumentException("Invalid email format");
        }

        // Map DTO to Customer Entity
        Customer customer = new Customer();
        customer.setUserRole(String.valueOf(UserRole.CUSTOMER));
        customer.setUserId(userIdGeneratorService.generateUserId(customer.getUserRole()));
        customer.setName(userDto.getName());
        customer.setEmail(userDto.getEmail());
        customer.setPasswordHash(hashPassword(userDto.getPassword()));
        customer.setPhoneNumber(customer.getPhoneNumber());
        customer.setShippingAddress(userDto.getShippingAddress());
        customer.setRegistrationDate(LocalDateTime.now());

        return customer;
    }

    /**
     * This method hashes the password using BCrypt hashing algorithm.
     * @param password
     * @return hashed password
     */
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    //validation methods
    /**
     * This method checks if the shipping address is valid.
     * Ensures it's not null and contains enough information to be usable.
     * @param shippingAddress
     * @return true if the shipping address is valid, false otherwise
     */
    // TODO : Check if the address is valid, not just whether it's not null
    private boolean isValidShippingAddress(String shippingAddress) {
        return shippingAddress != null && !shippingAddress.trim().isEmpty() && shippingAddress.length() >= 10;
    }

    /**
     * This method checks if the phone number is valid. Whether it contains only numbers and is 10 digits long.
     * @param phoneNumber
     * @return true if the phone number is valid, false otherwise
     */
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("^\\d{10}$");
    }

    /**
     * This method checks if the password is valid.
     * Whether it contains at least 8 characters, one uppercase letter, one lowercase letter, one number and one special character.
     * @param password
     * @return true if the password is valid, false otherwise
     */
    private boolean isValidPassword(String password) {
        return password != null && password.length() >= 8 && password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") && password.matches(".*[0-9].*") &&
                password.matches(".*[@#$%^&+=].*");
    }

    /**
     * This method checks if the email is valid.
     * Whether it starts with one or more letters, numbers, or special characters, followed by an @ symbol and a domain.
     * @param email
     * @return true if the email is valid, false otherwise
     */
    // TODO : Implement logic to check if it's an actual email, not just whether the syntax is correct. Let the user know what the issue is, like what is missing
    private boolean isValidEmail(String email) {
        if (customerRepository.existsCustomerByEmail(email)) {
            throw new IllegalArgumentException("Email already exists");
        }

        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}
