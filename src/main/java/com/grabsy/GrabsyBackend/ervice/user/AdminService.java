package com.grabsy.GrabsyBackend.ervice.user;

import com.grabsy.GrabsyBackend.constant.UserRole;
import com.grabsy.GrabsyBackend.dto.AdminDto;
import com.grabsy.GrabsyBackend.entity.users.Admin;
import com.grabsy.GrabsyBackend.exception.user.UserSaveException;
import com.grabsy.GrabsyBackend.repository.user.AdminRepository;
import com.grabsy.GrabsyBackend.ervice.SecurityService;
import com.grabsy.GrabsyBackend.ervice.UserIdGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminService{
    private final UserValidationService userValidationService;
    private final AdminRepository adminRepository;
    private final SecurityService securityService;
    private final UserIdGeneratorService userIdGeneratorService;
    private static final Logger log = LoggerFactory.getLogger(AdminService.class);
    private final SignedUserService signedUserService;

    public AdminService(UserValidationService userValidationService, AdminRepository adminRepository,
                        SecurityService securityService, UserIdGeneratorService userIdGeneratorService, SignedUserService signedUserService) {
        this.userValidationService = userValidationService;
        this.adminRepository = adminRepository;
        this.securityService = securityService;
        this.userIdGeneratorService = userIdGeneratorService;
        this.signedUserService = signedUserService;
    }

    public Admin registerAdmin(AdminDto adminDto) {
        // check validity of data
        userValidationService.validateName(adminDto.getName());
        userValidationService.validatePassword(adminDto.getPassword());
        userValidationService.validatePhoneNumber(adminDto.getPhoneNumber());
        userValidationService.validateEmail(adminRepository, adminDto.getEmail());

        // create admin
        Admin admin = new Admin();
        admin.setUserRole(String.valueOf(UserRole.ADMIN));
        admin.setUserId(userIdGeneratorService.generateUserId(UserRole.ADMIN));
        admin.setPasswordHash(securityService.hashPassword(adminDto.getPassword()));
        admin.setName(adminDto.getName());
        admin.setEmail(adminDto.getEmail());
        admin.setPhoneNumber(adminDto.getPhoneNumber());
        admin.setRegistrationDate(LocalDateTime.now());

        // register admin
        try {
            return adminRepository.save(admin);
        } catch (DataAccessException e) {
            log.error("Error saving admin to database", e);
            throw new UserSaveException("Unable to save admin to database", e);
        }
    }

    public Admin getAdminById(String userId){
        return signedUserService.getUserByIdOrThrow(userId, adminRepository);
    }

    public List<Admin> getAllAdmins(){
        return signedUserService.getAllUsersByRoleOrThrow(adminRepository);
    }

    public void removeAdminById(String userId){
        signedUserService.deleteUserByIdOrThrow(userId, adminRepository);
    }
}
