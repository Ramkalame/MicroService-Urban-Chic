package com.urbanchic.client;

import com.urbanchic.external.Product;
import com.urbanchic.util.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCTSERVICE")
public interface ProductServiceClient {

    @GetMapping("/products/{productId}")
    ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable("productId") String productId);
}
