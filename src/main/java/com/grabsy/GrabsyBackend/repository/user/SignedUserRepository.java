package com.grabsy.GrabsyBackend.repository.user;

import com.grabsy.GrabsyBackend.domain.SignedUser;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * 'T' allows the repository to work with any type that extends the SignedUser class.
 * 'T extends SignedUser', ensures that T must be a subclass of SignedUser.
 * 'T extends SignedUser, String' is the type of the ID field for the entity. In this case, it specifies that the ID of the entity is of type String
 * @param <T>
 */
public interface SignedUserRepository<T extends SignedUser, String> extends MongoRepository <T, String> {
}
