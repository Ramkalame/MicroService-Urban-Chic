package com.urbanchic.client;

import com.urbanchic.external.Seller;
import com.urbanchic.external.SellerDto;
import com.urbanchic.util.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "SELLERSERVICE")
public interface SellerServiceClient {

    @PostMapping("/api/v1/sellers/create")
    ApiResponse<Seller> createSeller(@RequestBody SellerDto sellerDto);
}
