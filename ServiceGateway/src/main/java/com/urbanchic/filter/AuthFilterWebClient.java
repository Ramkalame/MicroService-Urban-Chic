//package com.urbanchic.filter;
//
//import com.urbanchic.exception.MissingAuthorizationHeaderException;
//import com.urbanchic.exception.MissingAuthorizationTokenException;
//import com.urbanchic.exception.UnAuthorizeAccessToApplicationException;
//import com.urbanchic.util.ApiResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.reactive.function.client.WebClientResponseException;
//import reactor.core.publisher.Flux;
//
//@Component
//@Slf4j
//public class AuthFilterWebClient extends AbstractGatewayFilterFactory<AuthFilterWebClient.Config> {
//
//    private RouteValidator validator;
//    private WebClient webClient;
//
//    public AuthFilterWebClient(RouteValidator routeValidator, WebClient.Builder webClientBuilder){
//        super(Config.class);
//        this.validator = routeValidator;
//        this.webClient = webClientBuilder.build();
//    }
//
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return (exchange, chain) -> {
//            if (validator.isSecured.test(exchange.getRequest())) {
//                HttpHeaders headers = exchange.getRequest().getHeaders();
//                if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
//                    throw new MissingAuthorizationHeaderException("Missing Authorization Header");
//                }
//                String authToken = headers.getFirst("Authorization");
//                if (authToken == null || authToken.isBlank()) {
//                    throw new MissingAuthorizationTokenException("Missing Authorization Token");
//                }
//
//                log.info("{}: ","start calling");
//                // Call AuthServiceClient to verify role
//                return  this.webClient
//                        .get()
//                        .uri("http://AUTHSERVICE/users/verify/role")
//                        .header("Authorization",authToken)
//                        .retrieve()
//                        .bodyToMono(ApiResponse.class)
//                        .flatMap(apiResponse -> {
//                            log.info("Inside : {}","Inside the flatmap");
//                            if (apiResponse.getData().equals(true)){
//                                return chain.filter(exchange);
//                            }else {
//                                throw  new UnAuthorizeAccessToApplicationException("An unauthorized access to application 1.");
//                            }
//                        })
//                        .onErrorResume(WebClientResponseException.class, response -> {
//                            log.info("{}: " ,"inside the onerror");
//                            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
//                            exchange.getResponse().setStatusCode(response.getStatusCode());
//                            DataBuffer buffer = exchange
//                                    .getResponse()
//                                    .bufferFactory()
//                                    .wrap(response.getResponseBodyAsByteArray());
//                            return exchange.getResponse().writeWith(Flux.just(buffer));
//                        })
//                        .onErrorResume(throwable -> {
//                            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
//                            DataBuffer buffer = exchange
//                                    .getResponse()
//                                    .bufferFactory()
//                                    .wrap("An unauthorized access to application. 2".getBytes());
//                            return exchange.getResponse().writeWith(Flux.just(buffer));
//                        });
//            }
//            return chain.filter(exchange);
//        };
//    }
//
//    public static class Config{
//
//    }
//
//
//
//}
