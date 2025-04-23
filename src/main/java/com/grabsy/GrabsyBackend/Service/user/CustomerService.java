package com.grabsy.GrabsyBackend.service.user;

import com.grabsy.GrabsyBackend.constant.UserRole;
import com.grabsy.GrabsyBackend.dto.CustomerDto;
import com.grabsy.GrabsyBackend.entity.users.Customer;
import com.grabsy.GrabsyBackend.exception.user.InvalidShippingAddressException;
import com.grabsy.GrabsyBackend.repository.user.CustomerRepository;
import com.grabsy.GrabsyBackend.service.SecurityService;
import com.grabsy.GrabsyBackend.service.UserIdGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        userValidationService.validatePassword(customerDto.getPassword());
        userValidationService.validatePhoneNumber(customerDto.getPhoneNumber());
        userValidationService.validateEmail(customerRepository, customerDto.getEmail());
        validateShippingAddress(customerDto.getShippingAddress()); // TODO : Continue from here

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

        // TODO : What if customer is not created?
        return customerRepository.save(customer);
    }

    public List<Customer> findAllCustomers(){
        return getAllUsersByRole(customerRepository);
    }

    public Customer getCustomerById(String userId) {
        return getUserById(userId, customerRepository);
    }

    public void removeCustomer(String userId){
        deleteUserById(userId, customerRepository);
    }

    /**
     * This method checks if the shipping address is valid.
     * Ensures it's not null and contains enough information to be usable.
     * @param shippingAddress The shipping address to validate
     */
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
