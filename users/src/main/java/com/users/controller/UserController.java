package com.users.controller;

import com.users.dto.ApiResponse;
import com.users.dto.CreateUserDto;
import com.users.dto.LoginRequestDto;
import com.users.service.UserService;
import com.utility.dto.CreateOrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.naming.ServiceUnavailableException;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequestDto loginRequestDto) {
        return new ResponseEntity<>(userService.login(loginRequestDto), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createUser(@RequestBody CreateUserDto createUserDto) {
        return new ResponseEntity<>(userService.createUser(createUserDto), HttpStatus.OK);
    }

    @GetMapping("/order/{userId}")
    public ResponseEntity<ApiResponse> getAllOrdersByUserId(@PathVariable Long userId) throws ServiceUnavailableException {
        ApiResponse apiResponse = userService.getAllOrdersByUserId(userId);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("{userId}/order/create")
    public ResponseEntity<ApiResponse> createOrder(@PathVariable String userId, @RequestBody CreateOrderDto createOrderDto) {
        ApiResponse apiResponse = userService.createOrder(userId, createOrderDto);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
