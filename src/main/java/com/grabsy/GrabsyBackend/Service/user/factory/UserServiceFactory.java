package com.grabsy.GrabsyBackend.service.user.factory;

import com.grabsy.GrabsyBackend.assembler.AdminModelAssembler;
import com.grabsy.GrabsyBackend.assembler.CustomerModelAssembler;
import com.grabsy.GrabsyBackend.assembler.SellerModelAssembler;
import com.grabsy.GrabsyBackend.assembler.UserModelAssembler;
import com.grabsy.GrabsyBackend.constant.UserRole;
import com.grabsy.GrabsyBackend.domain.SignedUser;
import com.grabsy.GrabsyBackend.repository.user.AdminRepository;
import com.grabsy.GrabsyBackend.repository.user.CustomerRepository;
import com.grabsy.GrabsyBackend.repository.user.SellerRepository;
import com.grabsy.GrabsyBackend.repository.user.SignedUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class UserServiceFactory {
    private static final Logger log = LoggerFactory.getLogger(UserServiceFactory.class);
    // map for repositories
    private final Map<UserRole, SignedUserRepository<? extends SignedUser, String>> repositoryMap;

    // map for assemblers
    private final Map<UserRole, UserModelAssembler<? extends SignedUser>> assemblerMap;

    public UserServiceFactory(CustomerRepository customerRepository, SellerRepository sellerRepository,
                              AdminRepository adminRepository, CustomerModelAssembler customerModelAssembler,
                              SellerModelAssembler sellerModelAssembler, AdminModelAssembler adminModelAssembler) {
        // Initialize repository map
        this.repositoryMap = new EnumMap<>(UserRole.class);
        repositoryMap.put(UserRole.CUSTOMER, customerRepository);
        repositoryMap.put(UserRole.SELLER, sellerRepository);
        repositoryMap.put(UserRole.ADMIN, adminRepository);

        // Initialize assembler map
        this.assemblerMap = new EnumMap<>(UserRole.class);
        assemblerMap.put(UserRole.CUSTOMER, customerModelAssembler);
        assemblerMap.put(UserRole.SELLER, sellerModelAssembler);
        assemblerMap.put(UserRole.ADMIN, adminModelAssembler);
    }

    @SuppressWarnings("unchecked")
    public <T extends SignedUser> SignedUserRepository<T, String> getRepository(UserRole userRole) {
        return (SignedUserRepository<T, String>) repositoryMap.get(userRole);
    }

    @SuppressWarnings("unchecked")
    public <T extends SignedUser> UserModelAssembler<T> getModelAssembler(UserRole userRole){
        return (UserModelAssembler<T>) assemblerMap.get(userRole);
    }
}
