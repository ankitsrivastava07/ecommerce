package com.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@SpringBootApplication
public class ApiGatewayPatternApplication {
    AtomicReference<Set<String>> statusCode = new AtomicReference<>();

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayPatternApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        String httpUri = "";

        return builder.routes().route(p -> p.path("/api/v1/user/**")
                .filters(f -> f.addRequestHeader("Hello", "World")
                        .circuitBreaker(config ->
                            statusCode.set(config.setName("userService")
                                    //.setFallbackUri("forward:/fallback")
                                    .getStatusCodes())
                        )).uri("http://localhost:8081/")).build();
    }

/*    @RequestMapping("/fallback")
    public ResponseEntity<Map<String, Object>> userServiceFallback(Throwable exception) {

        Map<String, Object> response = new HashMap<>();
        response.put("error", exception.getLocalizedMessage());

        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }*/

/*    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("fallback");
    }*/
}