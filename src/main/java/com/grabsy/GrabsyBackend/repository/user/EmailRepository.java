package com.grabsy.GrabsyBackend.repository.user;

public interface EmailRepository {
    boolean existsByEmail(String email);
}
