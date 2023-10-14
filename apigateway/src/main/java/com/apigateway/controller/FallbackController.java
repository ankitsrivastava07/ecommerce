package com.apigateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.channels.ConnectionPendingException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class FallbackController {
    Logger logger = LoggerFactory.getLogger("");

/*    @RequestMapping("/fallback")
    public ResponseEntity<Map<String, Object>> userServiceFallback(Throwable throwable, ServerWebExchange webExchange) {
        logger.error("Exception occurred", throwable);
        Map<String, Object> response = new HashMap<>();

        if (throwable instanceof IOException) {
            response.put("error", webExchange);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        }

        response.put("success", "webExchange");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/


}
