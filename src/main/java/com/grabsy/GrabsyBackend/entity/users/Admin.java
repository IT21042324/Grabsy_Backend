package com.grabsy.GrabsyBackend.entity.users;

import com.grabsy.GrabsyBackend.domain.SignedUser;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This class represents an Admin entity in the system.
 */

// TODO : Is an admin class necessary since it doesn't seem to have it's own unique variables?
@Document(collection = "admins")
public class Admin extends SignedUser {
    public Admin(){}
}
