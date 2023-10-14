package com.utility.converter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DtoToMapConversion {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static <D> D convertDtoToMap(Object target, Class<D> destinationType) {
        return objectMapper.convertValue(target, destinationType);
    }
}
