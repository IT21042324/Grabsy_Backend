package com.grabsy.GrabsyBackend.util;

import java.util.Arrays;
import java.util.Optional;

/**
 * Utility class for copying non-null fields from one object to another using reflection.
 */
public class BeanReflectionUtil {

    /**
     * Copies all non-null fields from source to target object.
     * Only works if both objects are of the same class type.
     *
     * @param target the object to be updated
     * @param source the object containing updated values
     * @return Optional of updated target object if successful, otherwise Optional.empty()
     */
    public static <T> Optional<T> copyNonNullFields(T target, T source) {
        if (target == null || source == null || !target.getClass().equals(source.getClass())) {
            return Optional.empty();
        }

        Arrays.stream(source.getClass().getDeclaredFields()).peek(field -> field.setAccessible(true)).filter(field -> {
            try {
                return field.get(source) != null;
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Unable to access field: " + field.getName(), e);
            }
        }).forEach(field -> {
            try {
                field.set(target, field.get(source));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Unable to set field: " + field.getName(), e);
            }
        });

        return Optional.of(target);
    }
}
