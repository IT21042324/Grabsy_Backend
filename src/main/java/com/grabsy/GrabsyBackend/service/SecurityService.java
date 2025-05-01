package com.grabsy.GrabsyBackend.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**
 * This class is a service for security-related operations, such as hashing passwords.
 */

@Service
public class SecurityService {
    /**
     *
     * @param password
     * @return hashed password
     */
    public String hashPassword(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }
}
