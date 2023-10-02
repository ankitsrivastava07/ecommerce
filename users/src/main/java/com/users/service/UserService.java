package com.users.service;

import com.users.dto.ApiResponse;
import com.users.dto.CreateUserDto;
import com.users.dto.LoginRequestDto;

public interface UserService {

    ApiResponse login(LoginRequestDto loginRequestDto);

    ApiResponse createUser(CreateUserDto createUserDto);
}
