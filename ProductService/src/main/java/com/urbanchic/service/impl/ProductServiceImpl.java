package com.urbanchic.service.impl;

import com.urbanchic.dto.ProductDto;
import com.urbanchic.entity.Product;
import com.urbanchic.event.DeleteAllPrductImagesEvent;
import com.urbanchic.event.DeleteAllReviewsOfProductEvent;
import com.urbanchic.exception.EntityNotFoundException;
import com.urbanchic.repository.CustomProductRepository;
import com.urbanchic.repository.ProductRepository;
import com.urbanchic.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ApplicationEventPublisher eventPublisher;
    private final ProductRepository productRepository;
    private final CustomProductRepository customProductRepository;


    @Override
    public Product addProduct(ProductDto addProductDto) {

        Product newProduct = Product.builder()
                .productName(addProductDto.getProductName())
                .productPrice(addProductDto.getProductPrice())
                .productDescription(addProductDto.getProductDescription())
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

        //reviewService.deleteAllReviewByProductId(productId); Instead of this
        eventPublisher.publishEvent(new DeleteAllReviewsOfProductEvent(this,productId));
        eventPublisher.publishEvent(new DeleteAllPrductImagesEvent(this,productId));
        return "Product Deleted ID :" + productId;
    }

    @Override
    public List<Product> getAllProductsBySellerId(String sellerId) {
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
    public Product updateProductByProductId(String productId, ProductDto updateProductDto) {
        Product existingProduct = productRepository.findById(productId).orElseThrow(() ->
                new EntityNotFoundException("Product Does Not Exists"));

        existingProduct.setProductName(updateProductDto.getProductName());
        existingProduct.setProductPrice(updateProductDto.getProductPrice());
        existingProduct.setProductDescription(updateProductDto.getProductDescription());
        existingProduct.setProductQuantity(updateProductDto.getProductQuantity());
        existingProduct.setProductBrand(updateProductDto.getProductBrand());
        existingProduct.setProductCategory(updateProductDto.getProductCategory());
        existingProduct.setProductSubCategory(updateProductDto.getProductSubCategory());
        existingProduct.setProductType(updateProductDto.getProductType());
        existingProduct.setAttributes(updateProductDto.getAttributes());
        existingProduct.setVariants(updateProductDto.getVariants());
        existingProduct.setOneStarCount(updateProductDto.getOneStarCount());
        existingProduct.setTwoStarCount(updateProductDto.getTwoStarCount());
        existingProduct.setThreeStarCount(updateProductDto.getThreeStarCount());
        existingProduct.setFourStarCount(updateProductDto.getFourStarCount());
        existingProduct.setFiveStarCount(updateProductDto.getFiveStarCount());
        return productRepository.save(existingProduct);
    }

    @Override
    public Product getProductByProductId(String productId) {
        Product existingProduct = productRepository.findById(productId).orElseThrow(() -> {
                log.error("Product with ID: {} not found",productId);
            return new EntityNotFoundException("Product Does Not Exists");
        });

        return existingProduct;
    }

    @Override
    public List<Product> getAllProductByAttribute(String attributeName, String attributeValue) {
        List<Product> productList = customProductRepository.findAllProductsByAttributes(attributeName,attributeValue);
        if (productList.isEmpty()) {
            throw new EntityNotFoundException("Product Does Not Exist");
        }
        return productList;
    }

    @Override
    public List<Product> getProductByVariants(String variantName,String value) {
        List<Product> productList = customProductRepository.findAllProductsByVariants(variantName, value);
        if (productList.isEmpty()) {
            throw new EntityNotFoundException("Product Does Not Exist");
        }
        return productList;
    }

    @Override
    public List<Product> getAllProductsByProductCategory(String productCategory) {
        List<Product> productList = productRepository.findProductsByProductCategory(productCategory);
        if (productList.isEmpty()) {
            throw new EntityNotFoundException("Product Does Not Exist");
        }
        return productList;
    }

    @Override
    public List<Product> getAllProductsByProductSubCategory(String productSubCategory) {
        List<Product> productList = productRepository.findProductsByProductSubCategory(productSubCategory);
        if (productList.isEmpty()) {
            throw new EntityNotFoundException("Product Does Not Exist");
        }
        return productList;
    }

    @Override
    public List<Product> getAllProductsByProductType(String productType) {
        List<Product> productList = productRepository.findProductsByProductType(productType);
        if (productList.isEmpty()) {
            throw new EntityNotFoundException("Product Does Not Exist");
        }
        return productList;
    }
}
