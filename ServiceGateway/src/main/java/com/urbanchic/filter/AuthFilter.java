package com.urbanchic.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.urbanchic.util.ApiResponse;
import com.urbanchic.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class AuthFilter extends AbstractGatewayFilterFactory {

    @Autowired
    private RouteValidator routeValidator;
    @Autowired
    private  JwtUtil jwtUtil;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (routeValidator.isSecured.test(exchange.getRequest())){
                if (authMissing(request)){
                    return onError(exchange, HttpStatus.UNAUTHORIZED,"Authorization header is missing");
                }
                final String token = request.getHeaders().get("Authorization").get(0).substring(7);
                boolean isTokenExpired;
                try {
                    isTokenExpired = jwtUtil.isTokenExpired(token);
                }catch (Exception e){
                    return  onError(exchange,HttpStatus.UNAUTHORIZED,"Invalid Jwt Token!!");
                }
                if (isTokenExpired) {
                    return onError(exchange, HttpStatus.UNAUTHORIZED,"Token is Expired!!");
                }
                if (!userHasAccess(request,token)){
                    return onError(exchange, HttpStatus.FORBIDDEN,"Access Denied!!");
                }
            }
          return chain.filter(exchange);
        };
    }

    private boolean userHasAccess(ServerHttpRequest request,String token){
        String role = jwtUtil.getRolesFromToken(token);
        String uri = String.valueOf(request.getURI());
        log.info("URI ---- {}",uri);
        log.info("USER ROLE ---- {}",role);
        if (uri.contains("seller") && role.equals("ROLE_SELLER")){
            return  true;
        }
        return false;
    }


    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus,String message) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .data(null)
                .timestamp(LocalDateTime.now().toString())
                .message(message)
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .success(false)
                .build();
        DataBufferFactory bufferFactory = response.bufferFactory();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        DataBuffer dataBuffer;
        try {
            String jsonResponse = objectMapper.writeValueAsString(apiResponse);
            dataBuffer = bufferFactory.wrap(jsonResponse.getBytes());
        } catch (Exception e) {
            return Mono.error(e);
        }
        return response.writeWith(Mono.just(dataBuffer));
    }

    private boolean authMissing(ServerHttpRequest request){
        return !request.getHeaders().containsKey("Authorization");
    }


}
