package com.app.jagarv.service.products;

import org.springframework.stereotype.Service;

import com.app.jagarv.dto.product.create.CreateAttributeDTO;
import com.app.jagarv.entity.product.Attribute;

import com.app.jagarv.repository.product.AttributeRepository; 

import com.app.jagarv.exception.exceptions.products.AttributeAlreadyExistsException;

import com.app.jagarv.exception.exceptions.products.CategoryNotFoundException;

import com.app.jagarv.repository.product.ProductCategoryRepository;

import com.app.jagarv.entity.product.ProductCategory;

@Service 
public class AttributeService {
    private final AttributeRepository attributeRepository; 
    private final ProductCategoryRepository productCategoryRepository; 
    
    public AttributeService(
        AttributeRepository attributeRepository,
        ProductCategoryRepository productCategoryRepository
        ) {
        this.attributeRepository = attributeRepository;
        this.productCategoryRepository = productCategoryRepository;
    }
    
    public void createAttribute(CreateAttributeDTO newAttribute) {
        boolean attributeExists = attributeRepository.existsByName(newAttribute.getName());
        
        if(attributeExists) {
            throw new AttributeAlreadyExistsException("That attribute already exists, Try with another.");
        }
        
        ProductCategory category = productCategoryRepository.findById(newAttribute.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException("Category Not Found, Please Try Again...")); 
        
        
        Attribute attribute = new Attribute();
        
        attribute.setName(newAttribute.getName());
        attribute.setCategory(category);
        
        attributeRepository.save(attribute);
    }
}