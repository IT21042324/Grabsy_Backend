package com.grabsy.GrabsyBackend.service.user.factory;

import com.grabsy.GrabsyBackend.assembler.AdminModelAssembler;
import com.grabsy.GrabsyBackend.assembler.CustomerModelAssembler;
import com.grabsy.GrabsyBackend.assembler.SellerModelAssembler;
import com.grabsy.GrabsyBackend.assembler.UserModelAssembler;
import com.grabsy.GrabsyBackend.constant.UserRole;
import com.grabsy.GrabsyBackend.repository.user.AdminRepository;
import com.grabsy.GrabsyBackend.repository.user.CustomerRepository;
import com.grabsy.GrabsyBackend.repository.user.SellerRepository;
import com.grabsy.GrabsyBackend.repository.user.SignedUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserServiceFactory {
    private static final Logger log = LoggerFactory.getLogger(UserServiceFactory.class);
    // repositories
    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;
    private final AdminRepository adminRepository;

    // assemblers
    private final CustomerModelAssembler customerModelAssembler;
    private final SellerModelAssembler sellerModelAssembler;
    private final AdminModelAssembler adminModelAssembler;

    public UserServiceFactory(CustomerRepository customerRepository, SellerRepository sellerRepository,
                              AdminRepository adminRepository, CustomerModelAssembler customerModelAssembler,
                              SellerModelAssembler sellerModelAssembler, AdminModelAssembler adminModelAssembler) {
        this.customerRepository = customerRepository;
        this.sellerRepository = sellerRepository;
        this.adminRepository = adminRepository;
        this.customerModelAssembler = customerModelAssembler;
        this.sellerModelAssembler = sellerModelAssembler;
        this.adminModelAssembler = adminModelAssembler;
    }

    public SignedUserRepository getRepository(UserRole userRole){
        return switch (userRole) {
            case CUSTOMER -> customerRepository;
            case SELLER -> sellerRepository;
            case ADMIN -> adminRepository;
        };
    }

    public UserModelAssembler getModelAssembler(UserRole userRole){
        return switch (userRole) {
            case CUSTOMER -> customerModelAssembler;
            case SELLER -> sellerModelAssembler;
            case ADMIN -> adminModelAssembler;
        };
    }
}
