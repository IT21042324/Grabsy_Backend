package com.grabsy.GrabsyBackend.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import java.util.function.Supplier;

public class RepositoryUtils {
    private static final Logger log = LoggerFactory.getLogger(RepositoryUtils.class);

    // TODO : Update use of repository.save() throughout the project with this method.
    public static <T> T saveEntityWithExceptionHandling(
        Supplier<T> saveOperation,
        String errorMessage,
        RuntimeException exceptionToThrow
    ){
        try {
            return saveOperation.get();
        } catch (DataAccessException e){
            log.error(errorMessage, e);
            throw new RuntimeException(exceptionToThrow.getMessage(), e);
        }
    }
}
