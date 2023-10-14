package com.orders.service;

import com.utility.dto.ApiResponseDto;
import com.utility.dto.CreateOrderDto;

import java.util.List;

public interface OrderService {

    ApiResponseDto getOrdersByUserId(Long userId);

    ApiResponseDto createOrder(CreateOrderDto createOrderDto);
}
