package com.urbanchic.clients;

import com.urbanchic.external.User;
import com.urbanchic.utils.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "AUTHSERVICE")
public interface AuthServiceClient {

    @PutMapping("/api/v1/auth/account-status/{sellerId}")
    ApiResponse<User> updateSellerAccountStatus(@PathVariable("sellerId") String sellerId);
}
