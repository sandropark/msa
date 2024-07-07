package com.sandro.msa.saga.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static String writeValueAsString(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T readValue(String value, Class<T> orderCreatedEventClass) {
        try {
            return objectMapper.readValue(value, orderCreatedEventClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
