package com.grabsy.GrabsyBackend.ervice.user;

import com.grabsy.GrabsyBackend.dto.UpdateUserPhoneNumberDto;
import com.grabsy.GrabsyBackend.exception.user.attribute.InvalidPhoneNumberException;
import com.grabsy.GrabsyBackend.util.RepositoryUtils;
import com.grabsy.GrabsyBackend.domain.SignedUser;
import com.grabsy.GrabsyBackend.dto.UpdateUserPasswordDto;
import com.grabsy.GrabsyBackend.exception.user.UserDeletionException;
import com.grabsy.GrabsyBackend.exception.user.UserFetchException;
import com.grabsy.GrabsyBackend.exception.user.UserNotFoundException;
import com.grabsy.GrabsyBackend.exception.user.UserUpdateException;
import com.grabsy.GrabsyBackend.exception.user.attribute.InvalidPasswordException;
import com.grabsy.GrabsyBackend.ervice.SecurityService;
import com.grabsy.GrabsyBackend.ervice.user.factory.UserServiceFactory;
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
                    .orElseThrow(() -> new UserNotFoundException("User with id: " + userId + " could not be found"));
        } catch (DataAccessException e) {
            log.error("Error fetching user with id: {}", userId, e);
            throw new UserFetchException("User with id: "  + userId + " could not be fetched", e);
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
                log.error("User with id: {} could not be found for deletion", userId);
                throw new UserNotFoundException("User with id: " + userId + " could not be found for deletion");
            }

            repository.deleteById(userId);
        } catch (DataAccessException e) {
            log.error("Error deleting user with id: {}", userId, e);
            throw new UserDeletionException("User with id : " + userId + " could not be deleted", e);
        }
    }

    public <T extends SignedUser> T updatePassword(UpdateUserPasswordDto dto) {
        // validate input data
        userValidationService.validateUpdateUserPasswordDto(dto);

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

    public <T extends SignedUser> T updatePhoneNumber(UpdateUserPhoneNumberDto dto) {
        // validate input data
        userValidationService.validateUpdateUserPhoneNumberDto(dto);

        // get user
        MongoRepository<T, String> repository = userServiceFactory.getRepository(dto.getUserRole());
        T user = getUserByIdOrThrow(dto.getUserId(), repository);

        if (user.getPhoneNumber() == null){
            log.error("User with ID: {} does not have a phone number", dto.getUserId());
            throw new InvalidPhoneNumberException("User with ID: " + dto.getUserId() + " does not have a phone number");
        }

        if (user.getPhoneNumber().equals(dto.getNewPhoneNumber())){
            log.error("New phone number cannot match old phone number");
            throw new InvalidPhoneNumberException("New phone number cannot match old phone number");
        }

        // update details
        user.setPhoneNumber(dto.getNewPhoneNumber());

        // return user
        return RepositoryUtils.saveEntityWithExceptionHandling(
                () -> repository.save(user),
                "Error updating phoneNumber of user with ID : " + user.getUserId(),
                new UserUpdateException("Error updating phoneNumber of user with ID : " + user.getUserId())
        );
    }
}
