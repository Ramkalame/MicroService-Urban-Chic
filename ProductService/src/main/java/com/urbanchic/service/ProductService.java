package com.urbanchic.service;

import com.urbanchic.dto.ProductDto;
import com.urbanchic.dto.ProductImageDto;
import com.urbanchic.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {


    Product addProduct(ProductDto addProductDto);
    void addProductImage(String productId,List<ProductImageDto> productImageDtoList);
    String deleteProduct(String sellerId, String productId);
    Page<Product> getAllProductsBySellerId(String sellerId, Pageable pageable);
    List<Product> getAllProducts();
    Product updateProductByProductId(String productId,ProductDto updateProductDto);
    Product getProductByProductId(String productId);
    List<Product> getAllProductByAttribute(String attributeName, String attributeValue);
    List<Product> getProductByVariants(String variantName,String value);
    List<Product> getAllProductsByProductCategory(String productCategory);
    List<Product> getAllProductsByProductSubCategory(String productSubCategory);
    List<Product> getAllProductsByProductType(String productType);
    void changeProductActiveStatus(boolean status,String productId);

}
