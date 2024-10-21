package com.app.jagarv.service.admin.product;

import com.app.jagarv.dto.product.ProductDTO;
import com.app.jagarv.dto.product.CreateProductDTO;
import com.app.jagarv.entity.Product;
import com.app.jagarv.repository.ProductRepository;
import com.app.jagarv.mapper.product.ProductMapper;
import com.app.jagarv.service.cloudinary.CloudinaryService;


import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.io.IOException;

@Service 
public class ProductService {
    
    // INJECTIONS
    @Autowired 
    private ProductRepository productRepository;  
    
    @Autowired
    private ProductMapper productMapper; 
    
    @Autowired
    private CloudinaryService cloudinary;
    
    
    // RETURNS ALL THE PRODUCTS FROM THE DB TO THE CONTROLLER
    public List<ProductDTO> getProducts() {
        return productRepository.findAll()
            .stream()
            .map(productMapper::productToDTO)
            .collect(Collectors.toList());
    }
 
 
    // creates a new product
    public ProductDTO createProduct(CreateProductDTO createProductDTO, MultipartFile[] pictures) {
        if (productRepository.existsByTitle(createProductDTO.getTitle())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Product already exists.");
        }
    
        Product product = new Product();
        product.setTitle(createProductDTO.getTitle());
        product.setDescription(createProductDTO.getDescription());
        product.setCategory(createProductDTO.getCategory());
        product.setPrice(createProductDTO.getPrice());
    
        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile picture : pictures) {
            try {
                Map<String, Object> uploadResult = cloudinary.uploadImage(picture, "nombre_del_folder");
    
                if (uploadResult.get("url") != null) {
                    imageUrls.add(uploadResult.get("url").toString());
                } else {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Image upload failed: URL is null.");
                }
            } catch (IOException e) {
          
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error uploading image: " + e.getMessage(), e);
            }
        }
    
        product.setPictures(imageUrls); 
    
        Product savedProduct = productRepository.save(product);
        return productMapper.productToDTO(savedProduct);
    }
    
    
}