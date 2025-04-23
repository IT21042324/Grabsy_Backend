package com.grabsy.GrabsyBackend.service.user;

import com.grabsy.GrabsyBackend.constant.UserRole;
import com.grabsy.GrabsyBackend.dto.CustomerDto;
import com.grabsy.GrabsyBackend.entity.users.Customer;
import com.grabsy.GrabsyBackend.exception.user.InvalidShippingAddressException;
import com.grabsy.GrabsyBackend.exception.user.UserDeletionException;
import com.grabsy.GrabsyBackend.exception.user.UserFetchException;
import com.grabsy.GrabsyBackend.exception.user.UserSaveException;
import com.grabsy.GrabsyBackend.repository.user.CustomerRepository;
import com.grabsy.GrabsyBackend.service.SecurityService;
import com.grabsy.GrabsyBackend.service.UserIdGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
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
    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    // constructor
    public CustomerService(SecurityService securityService, CustomerRepository customerRepository,
                           UserValidationService userValidationService, UserIdGeneratorService userIdGeneratorService) {
        this.securityService = securityService;
        this.customerRepository = customerRepository;
        this.userValidationService = userValidationService;
        this.userIdGeneratorService = userIdGeneratorService;
    }

    public Customer registerCustomer(CustomerDto customerDto) {
        // validate the customerDto
        userValidationService.validateName(customerDto.getName());
        userValidationService.validatePassword(customerDto.getPassword());
        userValidationService.validatePhoneNumber(customerDto.getPhoneNumber());
        userValidationService.validateEmail(customerRepository, customerDto.getEmail());
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

        try {
            return customerRepository.save(customer);
        } catch (DataAccessException e) {
            log.error("Error saving customer to the database", e);
            throw new UserSaveException("Error saving customer to the database", e);
        }
    }

    public List<Customer> findAllCustomers(){
        try {
            return getAllUsersByRole(customerRepository);
        } catch (DataAccessException e) {
            log.error("Error fetching customers from the database", e);
            throw new UserFetchException("Error fetching customers from the database", e);
        }
    }

    public Customer getCustomerById(String userId) {
        try {
            return getUserById(userId, customerRepository);
        } catch (DataAccessException e) {
            log.error("Error fetching customer with id: {}", userId, e);
            throw new UserFetchException("Error fetching customer with id: " + userId, e);
        }
    }

    public void removeCustomerById(String userId){
        try {
            deleteUserById(userId, customerRepository);
        } catch (DataAccessException e) {
            log.error("Error deleting customer with id: {}", userId, e);
            throw new UserDeletionException("Error deleting customer with id: " + userId, e);
        }
    }

    // TODO : Check if the address is valid, not just whether it's not null
    private void validateShippingAddress(String shippingAddress) {
        if (shippingAddress == null || shippingAddress.trim().isEmpty()){
            log.error("Shipping address cannot be null");
            throw new InvalidShippingAddressException("Shipping address cannot be null");
        }

        if (shippingAddress.length() < 10) {
            log.error("Shipping address must be at least 10 characters");
            throw new InvalidShippingAddressException("Shipping address must be at least 10 characters");
        }
    }
}
