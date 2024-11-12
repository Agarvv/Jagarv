package com.app.jagarv.service.products;

import org.springframework.stereotype.Service;

import com.app.jagarv.dto.product.create.CreateCategoryDTO;
import com.app.jagarv.entity.product.ProductCategory;
import com.app.jagarv.repository.product.ProductCategoryRepository; 

import com.app.jagarv.exception.exceptions.products.CategoryAlreadyExistsException; 

@Service 
public class CategoryService {
    private final ProductCategoryRepository productCategoryRepository; 
    
    public CategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }
    
    // this service creates a new category
    public void createCategory(CreateCategoryDTO category) {
        
        boolean categoryExists = productCategoryRepository.existsByName(category.getName()); 
        
        if(categoryExists) {
            throw new CategoryAlreadyExistsException("That category already exists... Try with another category name");
        }
        
        ProductCategory productCategory = new ProductCategory(); 
        
        productCategory.setName(category.getName());
        productCategoryRepository.save(productCategory);
    }
}