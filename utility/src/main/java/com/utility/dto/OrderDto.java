package com.utility.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class OrderDto {

    List<Map<String, Object>> orders;
}
