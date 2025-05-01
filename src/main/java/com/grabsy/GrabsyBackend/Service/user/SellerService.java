package com.grabsy.GrabsyBackend.Service.user;

import com.grabsy.GrabsyBackend.constant.UserRole;
import com.grabsy.GrabsyBackend.dto.SellerDto;
import com.grabsy.GrabsyBackend.entity.users.Seller;
import com.grabsy.GrabsyBackend.exception.user.UserDeletionException;
import com.grabsy.GrabsyBackend.exception.user.UserFetchException;
import com.grabsy.GrabsyBackend.exception.user.UserSaveException;
import com.grabsy.GrabsyBackend.repository.user.SellerRepository;
import com.grabsy.GrabsyBackend.Service.SecurityService;
import com.grabsy.GrabsyBackend.Service.UserIdGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(SellerService.class);

    public SellerService(SellerRepository sellerRepository, SecurityService securityService,
                         UserValidationService userValidationService, UserIdGeneratorService userIdGeneratorService) {
        this.sellerRepository = sellerRepository;
        this.securityService = securityService;
        this.userValidationService = userValidationService;
        this.userIdGeneratorService = userIdGeneratorService;
    }

    public Seller getSellerById(String userId) {
        return getUserById(userId, sellerRepository);
    }

    public List<Seller> findAllSellers() {
        try {
            return getAllUsersByRole(sellerRepository);
        } catch (Exception e) {
            log.error("Error fetching all sellers", e);
            throw new UserFetchException("Unable to fetch all sellers", e);
        }
    }

    public void removeSellerById(String userId){
        try {
            deleteUserById(userId, sellerRepository);
        } catch (Exception e) {
            log.error("Error deleting seller with id: {}", userId, e);
            throw new UserDeletionException("Unable to delete seller with id: " + userId, e);
        }
    }

    public Seller registerSeller(SellerDto sellerDto) {
        // validate the sellerDto
        userValidationService.validateName(sellerDto.getName());
        userValidationService.validatePassword(sellerDto.getPassword());
        userValidationService.validatePhoneNumber(sellerDto.getPhoneNumber());
        userValidationService.validateEmail(sellerRepository, sellerDto.getEmail());

        // Map DTO to Seller Entity
        Seller seller =  new Seller();
        seller.setUserRole(String.valueOf(UserRole.SELLER));
        seller.setUserId(userIdGeneratorService.generateUserId(seller.getUserRole()));
        seller.setName(sellerDto.getName());
        seller.setEmail(sellerDto.getEmail());
        seller.setPasswordHash(securityService.hashPassword(sellerDto.getPassword()));
        seller.setPhoneNumber(sellerDto.getPhoneNumber());
        seller.setRegistrationDate(LocalDateTime.now());

        try {
            return sellerRepository.save(seller);
        } catch (Exception e) {
            log.error("Error registering seller", e);
            throw new UserSaveException("Unable to register seller", e);
        }
    }
}
