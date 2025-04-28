package com.grabsy.GrabsyBackend.service;

import com.grabsy.GrabsyBackend.constant.UserRole;
import com.grabsy.GrabsyBackend.entity.UserIdCounter;
import com.grabsy.GrabsyBackend.exception.user.UserIdGeneratorException;
import com.grabsy.GrabsyBackend.repository.UserIdCounterRepository;
import com.grabsy.GrabsyBackend.service.user.UserValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import static com.grabsy.GrabsyBackend.constant.ApplicationConstants.MAX_USER_ID_COUNTER_VALUE;

@Service
public class UserIdGeneratorService {
    private static final Logger log = LoggerFactory.getLogger(UserIdGeneratorService.class);
    private final UserIdCounterRepository userIdCounterRepository;
    private final UserValidationService userValidationService;

    public UserIdGeneratorService(UserIdCounterRepository userIdCounterRepository, UserValidationService userValidationService){
        this.userIdCounterRepository = userIdCounterRepository;
        this.userValidationService = userValidationService;
    }

    /**
     * This method generates a new user id based on the role of the user.
     * @param role the role of the user
     * @return a new user id
     */
    public synchronized String generateUserId(UserRole role){
        // Check if the role is valid
        userValidationService.validateUserRole(role);

        UserIdCounter counter;
        try {
            counter = userIdCounterRepository.findById(String.valueOf(role).toUpperCase())
                    .orElseGet(() -> new UserIdCounter(String.valueOf(role).toUpperCase(), 0));
        } catch (DataAccessException e) {
            log.error("Error fetching user ID counter for role: {}", role, e);
            throw new UserIdGeneratorException("Database operation failed while fetching user ID counter", e);
        }

        try {
            int newCounterValue = counter.getCounter() + 1;
            counter.setCounter(newCounterValue);
            userIdCounterRepository.save(counter);
            int digitWidth = String.valueOf(MAX_USER_ID_COUNTER_VALUE).length();

            return String.valueOf(role).toUpperCase().charAt(0) + String.format("%0" + digitWidth + "d", newCounterValue);
        } catch (DataAccessException e) {
            log.error("Error generating user ID for role: {}", role, e);
            throw new UserIdGeneratorException("Database operation failed while generating user ID", e);
        }
    }
}
