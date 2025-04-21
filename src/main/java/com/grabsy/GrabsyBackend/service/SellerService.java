package com.grabsy.GrabsyBackend.service;

import com.grabsy.GrabsyBackend.constant.UserRole;
import com.grabsy.GrabsyBackend.dto.SellerDto;
import com.grabsy.GrabsyBackend.entity.users.Seller;
import com.grabsy.GrabsyBackend.exception.UserNotFoundException;
import com.grabsy.GrabsyBackend.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class is a service for the Seller entity, it contains business logic related to sellers.
 */
@Service
public class SellerService extends SignedUserService {
    private final SellerRepository sellerRepository;
    private final SecurityService securityService;
    private final UserValidationService userValidationService;
    private final UserIdGeneratorService userIdGeneratorService;

    public SellerService(SellerRepository sellerRepository, SecurityService securityService,
                         UserValidationService userValidationService, UserIdGeneratorService userIdGeneratorService) {
        this.sellerRepository = sellerRepository;
        this.securityService = securityService;
        this.userValidationService = userValidationService;
        this.userIdGeneratorService = userIdGeneratorService;
    }

    /**
     * This method retrieves a seller by their ID.
     * @param userId The ID of the seller to retrieve.
     * @return The seller with the specified ID.
     * @throws UserNotFoundException If the seller with the specified ID is not found.
     */
    public Seller getSellerById(String userId) {
        return getUserById(userId, sellerRepository);
    }

    /**
     * This method retrieves all sellers.
     * @return A list of all sellers.
     */
    public List<Seller> findAllSellers() {
        return sellerRepository.findAll();
    }

    public Seller registerSeller(SellerDto sellerDto) {
        // validate the sellerDto
        userValidationService.validatePassword(sellerDto.getPassword());
        userValidationService.validatePhoneNumber(sellerDto.getPhoneNumber());
        userValidationService.validateEmail(UserRole.SELLER, sellerDto.getEmail());

        // Map DTO to Seller Entity
        Seller seller =  new Seller();
        seller.setUserRole(String.valueOf(UserRole.SELLER));
        seller.setUserId(userIdGeneratorService.generateUserId(seller.getUserRole()));
        seller.setName(sellerDto.getName());
        seller.setEmail(sellerDto.getEmail());
        seller.setPasswordHash(securityService.hashPassword(sellerDto.getPassword()));
        seller.setPhoneNumber(sellerDto.getPhoneNumber());
        seller.setRegistrationDate(LocalDateTime.now());

        return sellerRepository.save(seller);
    }
}
