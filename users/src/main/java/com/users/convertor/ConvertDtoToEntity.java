package com.users.convertor;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertDtoToEntity {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static <D> D dtoToEntityConvertor(Object fromSource, Class<D> toValueType) {
        return objectMapper.convertValue(fromSource, toValueType);
    }
}
