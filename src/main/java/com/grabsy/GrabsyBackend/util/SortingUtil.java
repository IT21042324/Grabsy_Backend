package com.grabsy.GrabsyBackend.util;

import org.springframework.data.domain.Sort;

public class SortingUtil {

    public static Sort SortPropertiesBy(Sort.Direction direction, String ...properties){
        return Sort.by(direction, properties);
    }
}
