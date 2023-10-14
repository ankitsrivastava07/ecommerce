package com.orders.service;

import java.util.*;

import com.utility.dto.ApiResponseDto;
import com.utility.dto.CreateOrderDto;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private List<CreateOrderDto> orders = new ArrayList<>();

    @Override
    public ApiResponseDto getOrdersByUserId(Long userId) {
        return ApiResponseDto.builder().setData(orders).setStatus(Boolean.TRUE).setMsg("Success").build();
    }

    @Override
    public ApiResponseDto createOrder(CreateOrderDto createOrderDto) {
        orders.add(createOrderDto);

        return ApiResponseDto.builder()
                .setMsg("Success")
                .setStatus(Boolean.TRUE)
                .setData(createOrderDto)
                .build();
    }
}
