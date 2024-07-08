package com.urbanchic.service;

import com.urbanchic.dto.ProductDto;
import com.urbanchic.entity.Product;

import java.util.List;

public interface ProductService {


    Product addProduct(ProductDto addProductDto);
    String deleteProduct(String productId);
    List<Product> getAllProductsBySeller(String sellerId);
    List<Product> getAllProducts();
    Product updateProductById(String productId,ProductDto updateProductDto);
    Product getProductByProductId(String productId);



}
