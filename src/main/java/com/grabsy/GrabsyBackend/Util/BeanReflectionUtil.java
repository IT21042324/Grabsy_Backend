package com.grabsy.GrabsyBackend.Util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BeanReflectionUtil  {
    public static Optional<Object> copyNotNullFieldsToObject(Object existing, Object objectWithChangesToUpdate) {
        if (Objects.equals(existing, null) || Objects.equals(objectWithChangesToUpdate, null) || existing.getClass() != objectWithChangesToUpdate.getClass())
            return Optional.empty();

        Arrays.stream(objectWithChangesToUpdate.getClass().getDeclaredFields()).peek(field -> field.setAccessible(true)).filter(field -> {
            try {
                return (field.get(objectWithChangesToUpdate) != null);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).forEach(field -> {
            try {
                field.set(existing, field.get(objectWithChangesToUpdate));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

        return Optional.of(existing);
    }
}
