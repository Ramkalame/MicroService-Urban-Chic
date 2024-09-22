package com.urbanchic.service.impl;

import com.urbanchic.dto.*;
import com.urbanchic.entity.Product;
import com.urbanchic.entity.ProductImage;
import com.urbanchic.entity.helper.Attribute;
import com.urbanchic.entity.helper.Variant;
import com.urbanchic.entity.helper.VariantAttribute;
import com.urbanchic.event.DeleteAllProductImagesEvent;
import com.urbanchic.event.DeleteAllReviewsOfProductEvent;
import com.urbanchic.exception.EntityNotFoundException;
import com.urbanchic.repository.CustomProductRepository;
import com.urbanchic.repository.ProductRepository;
import com.urbanchic.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        Product newProduct = new Product();
        //product basic details
        newProduct.setProductName(addProductDto.getProductName());
        newProduct.setProductDescription(addProductDto.getProductDescription());
        newProduct.setProductBrand(addProductDto.getProductBrand());
        newProduct.setProductCategory(addProductDto.getProductCategory());
        newProduct.setProductSubCategory(addProductDto.getProductSubCategory());
        newProduct.setProductType(addProductDto.getProductType());
        newProduct.setSellerId(addProductDto.getSellerId());
        newProduct.setActive(true);



        //product attribute
        List<Attribute> newAttributeList = new ArrayList<>();
        for (AttributeDto attributeDto: addProductDto.getAttributeDtoList()){
            Attribute newAttribute = Attribute.builder()
                    .attributeName(attributeDto.getAttributeName())
                    .attributeValue(attributeDto.getAttributeValue())
                    .build();
            newAttributeList.add(newAttribute);
        }
        newProduct.setAttributeList(newAttributeList);

        //variant
        List<Variant> newVariantList = new ArrayList<>();
        for (VariantDto variantDto: addProductDto.getVariantDtoList()){
            Variant newVariant = Variant.builder()
                    .variantPrice(variantDto.getVariantPrice())
                    .variantQuantity(variantDto.getVariantQuantity())
                    .build();
            //variant attribute list
            List<VariantAttribute> newVariantAttributeList = new ArrayList<>();
            for (VariantAttributeDto variantAttributeDto:variantDto.getVariantAttributeDtoList()){
                VariantAttribute newVariantAttribute = VariantAttribute.builder()
                        .variantAttributeName(variantAttributeDto.getVariantAttributeName())
                        .variantAttributeValue(variantAttributeDto.getVariantAttributeValue())
                        .build();
                newVariantAttributeList.add(newVariantAttribute);
            }
            newVariant.setVariantAttributes(newVariantAttributeList);
            newVariantList.add(newVariant);
        }
        newProduct.setVariantList(newVariantList);

        Product savedProduct = productRepository.save(newProduct);
        return savedProduct;
    }

    @Override
    public void addProductImage(String productId,List<ProductImageDto> productImageDtoList) {
        Product existingProduct = productRepository.findById(productId).orElseThrow(() ->
                new EntityNotFoundException("Product Does Not Exist"));
        productRepository.deleteById(productId);
        List<ProductImage> newProductImageList = new ArrayList<>();
        for (ProductImageDto productImageDto:productImageDtoList){
            ProductImage newProductImage= ProductImage.builder()
                    .publicId(productImageDto.getPublicId())
                    .publicImageUrl(productImageDto.getPublicImageUrl())
                    .build();
            newProductImageList.add(newProductImage);
        }
        existingProduct.setProductImageList(newProductImageList);
        productRepository.save(existingProduct);
    }

    @Override
    public String deleteProduct(String sellerId, String productId) {
        Product existingProduct = productRepository.findByProductIdAndSellerId(productId,sellerId).orElseThrow(() ->
                new EntityNotFoundException("Product Does Not Exist"));

        eventPublisher.publishEvent(new DeleteAllReviewsOfProductEvent(this,productId));
        if (!existingProduct.getProductImageList().isEmpty()){
            eventPublisher.publishEvent(new DeleteAllProductImagesEvent(this,existingProduct.getProductImageList()));
        }
        productRepository.deleteById(productId);
        return "Product Deleted ID :" + productId;
    }

    @Override
    public Page<Product> getAllProductsBySellerId(String sellerId, Pageable pageable) {
        Page<Product> productList = productRepository.findProductBySellerId(sellerId,pageable);
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

    @Override
    public void changeProductActiveStatus(boolean status, String productId) {
        Product existingProduct = productRepository.findById(productId).orElseThrow(() -> {
            log.error("Product with ID: {} not found",productId);
            return new EntityNotFoundException("Product Does Not Exists");
        });

        existingProduct.setActive(status);
        productRepository.save(existingProduct);
    }

    @Override
    public int getProductCountBySellerId(String sellerId) {
        return productRepository.countBySellerId(sellerId);
    }


}
