package com.urbanchic.service;

import com.urbanchic.dto.ProductDto;
import com.urbanchic.entity.Product;

import java.util.List;

public interface ProductService {


    Product addProduct(ProductDto addProductDto);
    String deleteProduct(String productId);
    List<Product> getAllProductsBySellerId(String sellerId);
    List<Product> getAllProducts();
    Product updateProductByProductId(String productId,ProductDto updateProductDto);
    Product getProductByProductId(String productId);
    List<Product> getAllProductByAttribute(String attributeName, String attributeValue);
    List<Product> getProductByVariants(String variantName,String value);
    List<Product> getAllProductsByProductCategory(String productCategory);
    List<Product> getAllProductsByProductSubCategory(String productSubCategory);
    List<Product> getAllProductsByProductType(String productType);

}
