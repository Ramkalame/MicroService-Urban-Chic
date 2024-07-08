package com.urbanchic.controller;

import com.urbanchic.dto.ProductDto;
import com.urbanchic.entity.Product;
import com.urbanchic.service.ProductService;
import com.urbanchic.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody @Valid ProductDto productDto) {
        Product responseData = productService.addProduct(productDto);

        ApiResponse<Product> apiResponse = ApiResponse.<Product>builder()
                .data(responseData)
                .message("Product is added successfully")
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.CREATED.value())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> deleteProductById(@PathVariable("productId") String productId) {
        String responseData = productService.deleteProduct(productId);

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .data(responseData)
                .message("Product is deleted successfully")
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<?> getAllProductsBySeller(@PathVariable("sellerId") String sellerId) {
        List<Product> responseData = productService.getAllProductsBySeller(sellerId);

        ApiResponse<List<Product>> apiResponse = ApiResponse.<List<Product>>builder()
                .data(responseData)
                .message("All Products of Seller : " + sellerId)
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        List<Product> responseData = productService.getAllProducts();

        ApiResponse<List<Product>> apiResponse = ApiResponse.<List<Product>>builder()
                .data(responseData)
                .message("All Products")
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable("productId") String productId, @RequestBody @Valid ProductDto updateProductDto) {
        Product responseData = productService.updateProductById(productId, updateProductDto);

        ApiResponse<Product> apiResponse = ApiResponse.<Product>builder()
                .data(responseData)
                .message("Product Updated Successfully " + responseData.getProductId())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable("productId") String productId){
        Product responseData = productService.getProductByProductId(productId);
        System.out.println(responseData.toString());

        ApiResponse<Product> apiResponse = ApiResponse.<Product>builder()
                .data(responseData)
                .message("Requested Product")
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
