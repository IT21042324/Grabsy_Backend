package com.grabsy.GrabsyBackend.service;

import com.grabsy.GrabsyBackend.constant.UserRole;
import com.grabsy.GrabsyBackend.dto.CustomerDto;
import com.grabsy.GrabsyBackend.entity.users.Customer;
import com.grabsy.GrabsyBackend.exception.UserNotFoundException;
import com.grabsy.GrabsyBackend.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class is a service for the Customer entity, it contains business logic related to customers.
 */

@Service
public class CustomerService extends SignedUserService{
    private final SecurityService securityService;
    private final CustomerRepository customerRepository;
    private final UserValidationService userValidationService;
    private final UserIdGeneratorService userIdGeneratorService;

    // constructor
    public CustomerService(SecurityService securityService, CustomerRepository customerRepository,
                           CustomerRepository customerRepository1, UserValidationService userValidationService,
                           UserIdGeneratorService userIdGeneratorService) {
        this.securityService = securityService;
        this.customerRepository = customerRepository1;
        this.userValidationService = userValidationService;
        this.userIdGeneratorService = userIdGeneratorService;
    }

    public Customer registerCustomer(CustomerDto customerDto) {
        // validate the customerDto
        // TODO : Let user know what exactly is the issue, whether it's not enough characters and so on
        userValidationService.validatePassword(customerDto.getPassword());
        userValidationService.validatePhoneNumber(customerDto.getPhoneNumber());
        userValidationService.validateEmail(UserRole.CUSTOMER, customerDto.getEmail());
        validateShippingAddress(customerDto.getShippingAddress());

        // Map DTO to Customer Entity
        Customer customer = new Customer();
        customer.setUserRole(String.valueOf(UserRole.CUSTOMER));
        customer.setUserId(userIdGeneratorService.generateUserId(customer.getUserRole()));
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPasswordHash(securityService.hashPassword(customerDto.getPassword()));
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setShippingAddress(customerDto.getShippingAddress());
        customer.setRegistrationDate(LocalDateTime.now());

        return customerRepository.save(customer);
    }

    /**
     * This method retrieves all customers.
     * @return A list of all customers.
     */
    public List<Customer> findAllCustomers(){
        return customerRepository.findAll();
    }

    /**
     * This method retrieves a customer by their ID.
     * @param userId The ID of the customer to retrieve.
     * @return The customer with the specified ID.
     * @throws UserNotFoundException If the customer with the specified ID is not found.
     */
    public Customer getCustomerById(String userId) {
        return getUserById(userId, customerRepository);
    }

    /**
     * This method checks if the shipping address is valid.
     * Ensures it's not null and contains enough information to be usable.
     * @param shippingAddress
     */
    // TODO : Check if the address is valid, not just whether it's not null
    private void validateShippingAddress(String shippingAddress) {
        if (shippingAddress == null || shippingAddress.trim().isEmpty() || shippingAddress.length() < 10) {
            throw new IllegalArgumentException("Invalid shipping address");
        }
    }
}
