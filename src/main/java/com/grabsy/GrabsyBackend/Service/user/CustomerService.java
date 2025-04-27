package com.grabsy.GrabsyBackend.service.user;

import com.grabsy.GrabsyBackend.constant.UserRole;
import com.grabsy.GrabsyBackend.dto.AddCustomerShippingAddressDto;
import com.grabsy.GrabsyBackend.dto.CustomerDto;
import com.grabsy.GrabsyBackend.entity.users.Customer;
import com.grabsy.GrabsyBackend.exception.user.*;
import com.grabsy.GrabsyBackend.exception.user.attribute.InvalidShippingAddressException;
import com.grabsy.GrabsyBackend.exception.user.attribute.InvalidUserIdException;
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
public class CustomerService{
    private final SecurityService securityService;
    private final CustomerRepository customerRepository;
    private final SignedUserService signedUserService;
    private final UserIdGeneratorService userIdGeneratorService;
    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
    private final UserValidationService userValidationService;

    // constructor
    // TODO : Decide whether to extend signeduserservice or create an instance of it to
    public CustomerService(SecurityService securityService, CustomerRepository customerRepository,
                           SignedUserService signedUserService, UserIdGeneratorService userIdGeneratorService,
                           UserValidationService userValidationService) {
        this.securityService = securityService;
        this.customerRepository = customerRepository;
        this.signedUserService = signedUserService;
        this.userIdGeneratorService = userIdGeneratorService;
        this.userValidationService = userValidationService;
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
        customer.setUserId(userIdGeneratorService.generateUserId(UserRole.CUSTOMER));
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

    public List<Customer> findAll(){
        try {
            return signedUserService.getAllUsersByRoleOrThrow(customerRepository);
        } catch (DataAccessException e) {
            log.error("Error fetching customers from the database", e);
            throw new UserFetchException("Error fetching customers from the database", e);
        }
    }

    public Customer getById(String userId) {
        try {
            return signedUserService.getUserByIdOrThrow(userId, customerRepository);
        } catch (DataAccessException e) {
            log.error("Error fetching customer with id: {}", userId, e);
            throw new UserFetchException("Error fetching customer with id: " + userId, e);
        }
    }

    public void removeById(String userId){
        try {
            signedUserService.deleteUserByIdOrThrow(userId, customerRepository);
        } catch (DataAccessException e) {
            log.error("Error deleting customer with id: {}", userId, e);
            throw new UserDeletionException("Error deleting customer with id: " + userId, e);
        }
    }

    public Customer updateShippingAddress(AddCustomerShippingAddressDto dto) {
        // validation of inputs
        validateUserId(dto.getUserId());
        validateShippingAddress(dto.getShippingAddress());

        Customer existingCustomer = getById(dto.getUserId());

        if (existingCustomer.getShippingAddress() != null
                && existingCustomer.getShippingAddress().equalsIgnoreCase(dto.getShippingAddress())){
            log.error("New shipping address cannot be same as current shipping address");
            throw new InvalidShippingAddressException("New shipping address cannot be same as current shipping address");
        }
        existingCustomer.setShippingAddress(dto.getShippingAddress());

        try {
            return customerRepository.save(existingCustomer);
        } catch (DataAccessException e) {
            log.error("Error saving customer shipping address", e);
            throw new UserUpdateException("Error saving customer shipping address", e);
        }
    }

    // helper methods
    // TODO : Check if the address is valid, not just whether it's not null
    private void validateShippingAddress(String shippingAddress) {
        if (shippingAddress == null || shippingAddress.trim().isEmpty()){
            log.error("Shipping address cannot be empty");
            throw new InvalidShippingAddressException("Shipping address cannot be empty");
        }

        if (shippingAddress.length() < 10) {
            log.error("Shipping address must be at least 10 characters");
            throw new InvalidShippingAddressException("Shipping address must be at least 10 characters");
        }
    }

    // TODO : Make the null check part a reusable method in uservalidation service
    private void validateUserId(String userId){
        userValidationService.userIdNullCheck(userId);

        if (userId.startsWith(UserRole.CUSTOMER.toString())){
            log.error("Invalid user, current user is not a customer");
            throw new InvalidUserIdException("Invalid user, current user is not a customer");
        }
    }

}
