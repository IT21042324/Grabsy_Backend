package com.grabsy.GrabsyBackend.Service;

import com.grabsy.GrabsyBackend.entity.UserIdCounter;
import com.grabsy.GrabsyBackend.repository.UserIdCounterRepository;
import org.springframework.stereotype.Service;

@Service
public class UserIdGeneratorService {
    private final UserIdCounterRepository userIdCounterRepository;

    public UserIdGeneratorService(UserIdCounterRepository userIdCounterRepository){
        this.userIdCounterRepository = userIdCounterRepository;
    }

    // TODO : Handle exceptions
    /**
     * This method generates a new user id based on the role of the user.
     * @param role the role of the user
     * @return a new user id
     */
    public synchronized String generateUserId(String role){
        UserIdCounter counter = userIdCounterRepository.findById(role.toUpperCase())
                .orElseGet(() -> new UserIdCounter(role.toUpperCase(), 0));

        int newCounterValue = counter.getCounter() + 1;
        counter.setCounter(newCounterValue);
        userIdCounterRepository.save(counter);

        return role.toUpperCase().charAt(0) + String.format("%03d", newCounterValue);
    }
}
