package com.urbanchic.service.impl;

import com.urbanchic.dto.ProductDto;
import com.urbanchic.entity.Product;
import com.urbanchic.entity.productenum.ProductColor;
import com.urbanchic.entity.productenum.ProductSize;
import com.urbanchic.exception.EntityNotFoundException;
import com.urbanchic.repository.ProductRepository;
import com.urbanchic.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public Product addProduct(ProductDto addProductDto) {

        Product newProduct = Product.builder()
                .productName(addProductDto.getProductName())
                .productPrice(addProductDto.getProductPrice())
                .productDescription(addProductDto.getProductDescription())
                .productImageUrl(addProductDto.getProductImageUrl())
                .productQuantity(addProductDto.getProductQuantity())
                .productBrand(addProductDto.getProductBrand())
                .productCategory(addProductDto.getProductCategory())
                .productSubCategory(addProductDto.getProductSubCategory())
                .productType(addProductDto.getProductType())
                .sellerId(addProductDto.getSellerId())
                .attributes(addProductDto.getAttributes())
                .variants(addProductDto.getVariants())
                .build();
        return productRepository.save(newProduct);
    }

    @Override
    public String deleteProduct(String productId) {
        Product existingProduct = productRepository.findById(productId).orElseThrow(() ->
                new EntityNotFoundException("Product Does Not Exist"));
        productRepository.deleteById(productId);
        return "Product ID :" + productId;
    }

    @Override
    public List<Product> getAllProductsBySeller(String sellerId) {
        List<Product> productList = productRepository.findProductBySellerId(sellerId);
        if (productList.isEmpty()) {
            throw new EntityNotFoundException("Product Does Not Exist");
        }
        return productList;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    @Override
    public Product updateProductById(String productId, ProductDto updateProductDto) {
        Product existingProduct = productRepository.findById(productId).orElseThrow(() ->
                new EntityNotFoundException("Product Does Not Exists"));

        existingProduct.setProductName(updateProductDto.getProductName());
        existingProduct.setProductPrice(updateProductDto.getProductPrice());
        existingProduct.setProductDescription(updateProductDto.getProductDescription());
        existingProduct.setProductImageUrl(updateProductDto.getProductImageUrl());
        existingProduct.setProductQuantity(updateProductDto.getProductQuantity());
        existingProduct.setProductBrand(updateProductDto.getProductBrand());
        existingProduct.setProductCategory(updateProductDto.getProductCategory());
        existingProduct.setProductSubCategory(updateProductDto.getProductSubCategory());
        existingProduct.setProductType(updateProductDto.getProductType());
        existingProduct.setAttributes(updateProductDto.getAttributes());
        existingProduct.setVariants(updateProductDto.getVariants());

        return productRepository.save(existingProduct);
    }

    @Override
    public Product getProductByProductId(String productId) {
        Product existingProduct = productRepository.findById(productId).orElseThrow(() ->
                new EntityNotFoundException("Product Does Not Exists"));
        return existingProduct;
    }

    @Override
    public List<Product> getProductByColor(String color, String productType) {
        List<Product> productList = productRepository.findByProductColor(color,productType);
        if (productList.isEmpty()) {
            throw new EntityNotFoundException("Product Does Not Exist");
        }
        return productList;
    }

    @Override
    public List<Product> getProductBySize(String size) {
        List<Product> productList = productRepository.findByProductSize(size);
        if (productList.isEmpty()) {
            throw new EntityNotFoundException("Product Does Not Exist");
        }
        return productList;
    }
}
