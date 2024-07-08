//package com.urbanchic.filter;
//
//import com.urbanchic.client.AuthServiceClient;
//import com.urbanchic.exception.MissingAuthorizationHeaderException;
//import com.urbanchic.exception.MissingAuthorizationTokenException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import feign.Logger;
//
//@Component
//@Slf4j
//public class AuthFilter extends AbstractGatewayFilterFactory<AbstractGatewayFilterFactory.NameConfig> {
//
//    @Autowired
//    private RouteValidator validator;
//
//    @Autowired
//    private AuthServiceClient authServiceClient;
//
//    public AuthFilter() {
//        super(NameConfig.class);
//    }
//
//    @Override
//    public GatewayFilter apply(NameConfig config) {
//        return (exchange, chain) -> {
//            if (validator.isSecured.test(exchange.getRequest())) {
//                HttpHeaders headers = exchange.getRequest().getHeaders();
//
//                if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
//                    throw new MissingAuthorizationHeaderException("Missing Authorization Header");
//                }
//
//                String authToken = headers.getFirst("Authorization");
//
//                if (authToken == null || authToken.isBlank()) {
//                    throw new MissingAuthorizationTokenException("Missing Authorization Token");
//                }
//
//                // Call AuthServiceClient to verify role
//                return authServiceClient.verifyRole(authToken)
//                        .flatMap(apiResponse -> {
//                            if (apiResponse.getData()) {
//                                // If authorized, continue with the request
//                                return chain.filter(exchange);
//                            } else {
//                                // If unauthorized, return unauthorized response
//                                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                                return exchange.getResponse().setComplete();
//                            }
//                        })
//                        .onErrorResume(throwable -> {
//                            // Handle any errors (e.g., Feign exceptions)
//                            log.error("Error verifying role:", throwable);
//                            exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
//                            return exchange.getResponse().setComplete();
//                        });
//
//            }
//            return chain.filter(exchange);
//        };
//    }
//
//    @Configuration
//    public static class FeignConfig {
//
//        @Bean
//        public HttpMessageConverters httpMessageConverters() {
//            return new HttpMessageConverters();
//        }
//
//        @Bean
//        Logger.Level feignLoggerLevel() {
//            return Logger.Level.FULL;
//        }
//    }
//}
//
////                Mono<Boolean> authResultMono = Mono.fromCallable(() -> authServiceClient.verifyRole(authToken))
////                        .subscribeOn(Schedulers.boundedElastic())
////                        .doOnSuccess(isValid -> log.info("Auth Service Response: {}", isValid))
////                        .doOnError(e -> log.error("Error calling Auth Service", e));
////
////                return authResultMono.flatMap(isValid -> {
////                            if (Boolean.TRUE.equals(isValid)) {
////                                return chain.filter(exchange);
////                            } else {
////                                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
////                                return exchange.getResponse().setComplete();
////                            }
////                        })
////                        .onErrorResume(e -> {
////                            log.error("Exception during auth verification", e);
////                            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
////                            return exchange.getResponse().setComplete();
////                        });
