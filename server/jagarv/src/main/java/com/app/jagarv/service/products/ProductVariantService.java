package com.app.jagarv.service.products;

import org.springframework.stereotype.Service;
import com.app.jagarv.dto.product.CreateProductVariantDTO;
import com.app.jagarv.repository.product.ProductRepository; 
import com.app.jagarv.repository.product.ProductVariantRepository; 
import com.app.jagarv.repository.product.AttributeOptionRepository;
import com.app.jagarv.exception.exceptions.products.ProductNotFoundException;
import com.app.jagarv.entity.product.AttributeOption;
import com.app.jagarv.entity.product.ProductVariant;
import com.app.jagarv.entity.product.Product;

@Service 
public class ProductVariantService {
    
    private final ProductRepository productRepository; 
    private final ProductVariantRepository productVariantRepository; 
    private final AttributeOptionRepository attributeOptionRepository; 
    
    public ProductVariantService(
        ProductRepository productRepository,
        ProductVariantRepository productVariantRepository,
        AttributeOptionRepository attributeOptionRepository
    ) {
        this.productRepository = productRepository;
        this.productVariantRepository = productVariantRepository;
        this.attributeOptionRepository = attributeOptionRepository;
    }
    
    public void createProductVariant(@RequestBody CreateProductVariantDTO variant) {
        Product product = productRepository.findById(variant.getProductId()).orElseThrow(()
        -> new ProductNotFoundException("Product not found. try with another")
        )
        
        List<AttributeOption> options = attributeOptionRepository.findAllById(variant.getOptionsIds());
        
        ProductVariant newVariant = new ProductVariant(); 
        newVariant.setProductId(product.getId()); 
        newVariant.setPrice(variant.getPrice()); 
        newVariant.setStock(variant.getStock()); 
        newVariant.setImages(variant.getImages());
        newVariant.setOptions(options);
        
        productVariantRepository.save(newVariant);
        
    }
}