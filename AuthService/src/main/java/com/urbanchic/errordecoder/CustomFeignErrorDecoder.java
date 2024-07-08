package com.urbanchic.errordecoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.urbanchic.exception.CustomFeignException;
import com.urbanchic.util.ApiResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CustomFeignErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public Exception decode(String methodKey, Response response) {
        log.info("Error Decoder: {}, {}",methodKey,response);
        try {

            // Read the response body as a string
            String body = new BufferedReader(new InputStreamReader(response.body().asInputStream(), StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));

            //map the converted string body to apiResponse object
            ApiResponse<?> apiResponse = objectMapper.readValue(body, ApiResponse.class);

            // Return a custom exception containing the ApiResponse
            return new CustomFeignException(apiResponse, HttpStatus.valueOf(response.status()));
        } catch (Exception e) {
            log.error("Error while decoding the error response", e);
            return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

    CustomFeignErrorDecoder(){
        // Registering the JavaTimeModule to handle LocalDateTime
        objectMapper.registerModule(new JavaTimeModule());
    }

}
