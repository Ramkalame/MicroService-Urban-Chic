package com.urbanchic.service.impl;

import com.urbanchic.dto.ProductDto;
import com.urbanchic.entity.Product;
import com.urbanchic.entity.productenum.ProductColor;
import com.urbanchic.entity.productenum.ProductSize;
import com.urbanchic.exception.ProductNotExistException;
import com.urbanchic.repository.ProductRepository;
import com.urbanchic.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public Product addProduct(ProductDto addProductDto) {

        Product newProduct = new Product();
        newProduct.setProductName(addProductDto.getProductName());
        newProduct.setProductPrice(addProductDto.getProductPrice());
        newProduct.setProductDescription(addProductDto.getProductDescription());
        newProduct.setProductImageUrl(addProductDto.getProductImageUrl());
        newProduct.setProductQuantity(addProductDto.getProductQuantity());
        newProduct.setProductBrand(addProductDto.getProductBrand());
        newProduct.setProductCategory(addProductDto.getProductCategory());
        newProduct.setProductSubCategory(addProductDto.getProductSubCategory());
        newProduct.setProductType(addProductDto.getProductType());
        newProduct.setSellerId(addProductDto.getSellerId());

        for (ProductColor color : ProductColor.values()) {
            if (color.name().equals(addProductDto.getProductColor())) {
                newProduct.setProductColor(color);
            }
        }
        for (ProductSize size : ProductSize.values()) {
            if (size.name().equals(addProductDto.getProductSize())) {
                newProduct.setProductSize(size);
            }
        }
        return productRepository.save(newProduct);
    }

    @Override
    public String deleteProduct(String productId) {
            Product existingProduct = productRepository.findById(productId).orElseThrow( () ->
                    new ProductNotExistException("Product Does Not Exist"));
        productRepository.deleteById(productId);
        return  "Product ID :"+productId;
    }

    @Override
    public List<Product> getAllProductsBySeller(String sellerId) {
        List<Product> productList = productRepository.findProductBySellerId(sellerId);
        if (productList.isEmpty()){
            throw  new ProductNotExistException("Product Does Not Exist");
        }
        return  productList;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    @Override
    public Product updateProductById(String productId, ProductDto updateProductDto) {
        Product existingProduct = productRepository.findById(productId).orElseThrow(() ->
                new ProductNotExistException("Product Does Not Exists"));

        existingProduct.setProductName(updateProductDto.getProductName());
        existingProduct.setProductPrice(updateProductDto.getProductPrice());
        existingProduct.setProductDescription(updateProductDto.getProductDescription());
        existingProduct.setProductImageUrl(updateProductDto.getProductImageUrl());
        existingProduct.setProductQuantity(updateProductDto.getProductQuantity());
        existingProduct.setProductBrand(updateProductDto.getProductBrand());
        existingProduct.setProductCategory(updateProductDto.getProductCategory());
        existingProduct.setProductSubCategory(updateProductDto.getProductSubCategory());
        existingProduct.setProductType(updateProductDto.getProductType());
        existingProduct.setSellerId(updateProductDto.getSellerId());

        for (ProductColor color : ProductColor.values()) {
            if (color.name().equals(updateProductDto.getProductColor())) {
                existingProduct.setProductColor(color);
            }
        }
        for (ProductSize size : ProductSize.values()) {
            if (size.name().equals(updateProductDto.getProductSize())) {
                existingProduct.setProductSize(size);
            }
        }
        return productRepository.save(existingProduct);
    }

    @Override
    public Product getProductByProductId(String productId) {
        Product existingProduct = productRepository.findById(productId).orElseThrow(()->
                new ProductNotExistException("Product Does Not Exists"));
        return existingProduct;
    }
}
