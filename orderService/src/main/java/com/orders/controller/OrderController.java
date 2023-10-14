package com.orders.controller;

import com.orders.service.OrderService;
import com.utility.dto.ApiResponseDto;
import com.utility.dto.CreateOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getOrdersByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(orderService.getOrdersByUserId(userId), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<ApiResponseDto> createOrder(@RequestBody CreateOrderDto createOrderDto) {
        ApiResponseDto apiResponseDto = orderService.createOrder(createOrderDto);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }
}
