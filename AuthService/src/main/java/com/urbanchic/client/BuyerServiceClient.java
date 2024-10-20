package com.urbanchic.client;

import com.urbanchic.external.BuyerDto;
import com.urbanchic.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "USERSERVICE")
public interface BuyerServiceClient {

    @PostMapping("/api/v1/buyers/create")
    public ResponseEntity<ApiResponse<?>> createBuyer(@RequestBody @Valid BuyerDto buyerDto);

}
