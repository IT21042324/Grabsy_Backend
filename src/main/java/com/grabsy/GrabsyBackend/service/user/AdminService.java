package com.grabsy.GrabsyBackend.service.user;

import com.grabsy.GrabsyBackend.constant.UserRole;
import com.grabsy.GrabsyBackend.dto.AdminDto;
import com.grabsy.GrabsyBackend.entity.users.Admin;
import com.grabsy.GrabsyBackend.exception.user.UserSaveException;
import com.grabsy.GrabsyBackend.repository.user.AdminRepository;
import com.grabsy.GrabsyBackend.service.SecurityService;
import com.grabsy.GrabsyBackend.service.UserIdGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminService extends SignedUserService{
    private final UserValidationService userValidationService;
    private final AdminRepository adminRepository;
    private final SecurityService securityService;
    private final UserIdGeneratorService userIdGeneratorService;
    private static final Logger log = LoggerFactory.getLogger(AdminService.class);

    public AdminService(UserValidationService userValidationService, AdminRepository adminRepository,
                        SecurityService securityService, UserIdGeneratorService userIdGeneratorService) {
        this.userValidationService = userValidationService;
        this.adminRepository = adminRepository;
        this.securityService = securityService;
        this.userIdGeneratorService = userIdGeneratorService;
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
        admin.setUserId(userIdGeneratorService.generateUserId(admin.getUserRole()));
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
        return getUserById(userId, adminRepository);
    }

    public List<Admin> getAllAdmins(){
        return getAllUsersByRole(adminRepository);
    }

    public void removeAdminById(String userId){
        deleteUserById(userId, adminRepository);
    }
}
