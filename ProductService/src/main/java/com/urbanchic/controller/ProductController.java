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
        List<Product> responseData = productService.getAllProductsBySellerId(sellerId);

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
        Product responseData = productService.updateProductByProductId(productId, updateProductDto);

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

    @GetMapping("/attribute/{attributeName}/{attributeValue}")
    public ResponseEntity<?> getAllProductsByAttributes(@PathVariable("attributeName") String attributeName, @PathVariable("attributeValue") String attributeValue) {
        List<Product> responseData = productService.getAllProductByAttribute(attributeName,attributeValue);

        ApiResponse<List<Product>> apiResponse = ApiResponse.<List<Product>>builder()
                .data(responseData)
                .message("All Products with requested color")
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }

    @GetMapping("/variant/{variantName}/{variantValue}")
    public ResponseEntity<?> getAllProductsBySize(@PathVariable("variantName") String variantName,@PathVariable("variantValue") String variantValue) {
        List<Product> responseData = productService.getProductByVariants(variantName,variantValue);

        ApiResponse<List<Product>> apiResponse = ApiResponse.<List<Product>>builder()
                .data(responseData)
                .message("All Products with requested size")
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }

    @GetMapping("/category/{productCategory}")
    public ResponseEntity<?> getAllProductsByProductCategory(@PathVariable("productCategory") String productCategory) {
        List<Product> responseData = productService.getAllProductsByProductCategory(productCategory);

        ApiResponse<List<Product>> apiResponse = ApiResponse.<List<Product>>builder()
                .data(responseData)
                .message("All Products of Category : " + productCategory)
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }

    @GetMapping("/subcategory/{productSubCategory}")
    public ResponseEntity<?> getAllProductsByProductSubCategory(@PathVariable("productSubCategory") String productSubCategory) {
        List<Product> responseData = productService.getAllProductsByProductSubCategory(productSubCategory);

        ApiResponse<List<Product>> apiResponse = ApiResponse.<List<Product>>builder()
                .data(responseData)
                .message("All Products of Sub Category : " + productSubCategory)
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }

    @GetMapping("/type/{productType}")
    public ResponseEntity<?> getAllProductsByProductType(@PathVariable("productType") String productType) {
        List<Product> responseData = productService.getAllProductsByProductType(productType);

        ApiResponse<List<Product>> apiResponse = ApiResponse.<List<Product>>builder()
                .data(responseData)
                .message("All Products of Type : " + productType)
                .timestamp(LocalDateTime.now())
                .success(true)
                .statusCode(HttpStatus.OK.value())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }

}
