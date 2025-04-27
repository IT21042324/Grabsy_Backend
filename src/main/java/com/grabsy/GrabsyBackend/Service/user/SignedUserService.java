package com.grabsy.GrabsyBackend.service.user;

import com.grabsy.GrabsyBackend.util.RepositoryUtils;
import com.grabsy.GrabsyBackend.domain.SignedUser;
import com.grabsy.GrabsyBackend.dto.UpdateUserPasswordDto;
import com.grabsy.GrabsyBackend.exception.user.UserDeletionException;
import com.grabsy.GrabsyBackend.exception.user.UserFetchException;
import com.grabsy.GrabsyBackend.exception.user.UserNotFoundException;
import com.grabsy.GrabsyBackend.exception.user.UserUpdateException;
import com.grabsy.GrabsyBackend.exception.user.attributes.InvalidPasswordException;
import com.grabsy.GrabsyBackend.service.SecurityService;
import com.grabsy.GrabsyBackend.service.user.factory.UserServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class is a placeholder for the SignedUserService, it contains business logic related to signed users.
 */

@Service
public class SignedUserService {
    private static final Logger log = LoggerFactory.getLogger(SignedUserService.class);
    protected final UserValidationService userValidationService;
    private final UserServiceFactory userServiceFactory;
    private final SecurityService securityService;

    public SignedUserService(UserValidationService userValidationService, UserServiceFactory userServiceFactory, SecurityService securityService) {
        this.userValidationService = userValidationService;
        this.userServiceFactory = userServiceFactory;
        this.securityService = securityService;
    }

    protected <T> T getUserByIdOrThrow(String userId, MongoRepository<T, String> repository){
        try {
            return repository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("Seller with id: " + userId + " could not be found"));
        } catch (DataAccessException e) {
            log.error("Error fetching seller with id: {}", userId, e);
            throw new UserFetchException("Seller with id: "  + userId + " could not be fetched", e);
        }
    }

    protected <T> List<T> getAllUsersByRoleOrThrow(MongoRepository<T, String> repository){
        try{
            return repository.findAll();
        } catch (DataAccessException e){
            log.error("Error fetching users from repository", e);
            throw new UserFetchException("Unable to fetch users from repository", e);
        }
    }

    protected <T> void deleteUserByIdOrThrow(String userId, MongoRepository<T, String> repository){
        try {
            if(!repository.existsById(userId)){
                log.error("Seller with id: {} could not be found for deletion", userId);
                throw new UserNotFoundException("Seller with id: " + userId + " could not be found for deletion");
            }

            repository.deleteById(userId);
        } catch (DataAccessException e) {
            log.error("Error deleting seller with id: {}", userId, e);
            throw new UserDeletionException("Seller with id : " + userId + " could not be deleted", e);
        }
    }

    public <T extends SignedUser> T updatePassword(UpdateUserPasswordDto dto) {

        // validate new password
        userValidationService.validatePassword(dto.getNewPassword());
        userValidationService.validatePassword(dto.getRetypedNewPassword());

        // userId validation
        userValidationService.userIdNullCheck(dto.getUserId());

        // check if new and retyped password match
        if (!dto.getNewPassword().equals(dto.getRetypedNewPassword())){
            log.error("New password does not match retyped password");
            throw new InvalidPasswordException("New password does not match retyped password");
        }

        // find repository
        MongoRepository<T, String> repository = userServiceFactory.getRepository(dto.getUserRole());

        // fetch user
        T user = getUserByIdOrThrow(dto.getUserId(), repository);

        // null check for old password
        if (user.getPasswordHash() == null) {
            log.error("User with ID: {} does not have a password", dto.getUserId());
            throw new InvalidPasswordException("User with ID: " + dto.getUserId() + " does not have a password");
        }

        // check if old and current passwords match
        if (!(securityService.verifyPassword(dto.getOldPassword(), user.getPasswordHash()))){
            // print the hashed old password and the user's password hash from 'user' just to check
            log.error("Incorrect old password");
            throw new InvalidPasswordException("Incorrect old password");
        }

        if (dto.getRetypedNewPassword().equals(dto.getOldPassword())){
            log.error("New password is same as old password");
            throw new InvalidPasswordException("New password is same as old password");
        }

        // update user
        user.setPasswordHash(securityService.hashPassword(dto.getNewPassword()));

        // return user
        return RepositoryUtils.saveEntityWithExceptionHandling(
                () -> repository.save(user),
                "Error updating password of user with ID : " + user.getUserId(),
                new UserUpdateException("Error updating password of user with ID : " + user.getUserId())
        );
    }
}
