package com.users.service;

import com.users.dto.ApiResponse;
import com.users.dto.CreateUserDto;
import com.users.dto.LoginRequestDto;
import com.utility.dto.CreateOrderDto;

import javax.naming.ServiceUnavailableException;

public interface UserService {

    ApiResponse login(LoginRequestDto loginRequestDto);

    ApiResponse createUser(CreateUserDto createUserDto);

    ApiResponse getAllOrdersByUserId(Long userId) throws ServiceUnavailableException;

    ApiResponse createOrder(String userId, CreateOrderDto createOrderDto);
}
