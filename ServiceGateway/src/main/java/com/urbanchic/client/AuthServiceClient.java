//package com.urbanchic.client;
//
//import com.urbanchic.util.ApiResponse;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestHeader;
//import reactivefeign.spring.config.ReactiveFeignClient;
//import reactor.core.publisher.Mono;
//
//@Component
//@ReactiveFeignClient(name = "AUTHSERVICE")
//public interface AuthServiceClient {
//
//    @GetMapping("/users/verify/role")
//    Mono<ApiResponse<Boolean>> verifyRole(@RequestHeader("Authorization") String authToken);
//
//}
