package com.grabsy.GrabsyBackend.entity.users;

import com.grabsy.GrabsyBackend.domain.SignedUser;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This class represents an Admin entity in the system.
 */

@Document(collection = "admins")
public class Admin extends SignedUser {
    public Admin(){}
}
