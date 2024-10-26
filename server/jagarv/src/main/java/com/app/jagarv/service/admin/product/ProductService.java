package com.app.jagarv.service.admin.product;

import com.app.jagarv.dto.product.ProductDTO;
import com.app.jagarv.dto.product.CreateProductDTO;
import com.app.jagarv.entity.Product;
import com.app.jagarv.repository.ProductRepository;
import com.app.jagarv.mapper.product.ProductMapper;
import com.app.jagarv.exception.exceptions.products.ProductAlreadyExistsException;
import com.app.jagarv.exception.exceptions.products.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.Optional;

@Service 
public class ProductService {

    // INJECTIONS
    @Autowired 
    private ProductRepository productRepository;  

    @Autowired
    private ProductMapper productMapper;

    // RETURNS ALL THE PRODUCTS FROM THE DB TO THE CONTROLLER
    public List<ProductDTO> getProducts() {
        return productRepository.findAll()
            .stream()
            .map(productMapper::productToDTO)
            .collect(Collectors.toList());
    }

    // CREATES A NEW PRODUCT
    public ProductDTO createProduct(CreateProductDTO createProductDTO) {
        // Products title should be unique.
        if (productRepository.existsByTitle(createProductDTO.getTitle())) {
            throw new ProductAlreadyExistsException("The Product Already Exists, Try with another Title.");
        }

        Product product = new Product();
        product.setTitle(createProductDTO.getTitle());
        product.setDescription(createProductDTO.getDescription());
        product.setCategory(createProductDTO.getCategory());
        product.setPrice(createProductDTO.getPrice());
        product.setPictures(Arrays.asList(createProductDTO.getPictures()));
        // Set featured and stock if necessary
        product.setFeatured(createProductDTO.getFeatured());
        product.setStock(createProductDTO.getStock());

        Product savedProduct = productRepository.save(product);
        return productMapper.productToDTO(savedProduct);
    }

    // DELETE A PRODUCT
    public void deleteProduct(Long productId) {
        Boolean productExists = productRepository.existsById(productId);
        if (!productExists) {
            throw new ProductNotFoundException("The product that you want to delete does not exist...");
        }

        productRepository.deleteById(productId); // Product is deleted successfully.
    }

    // FEATURE A PRODUCT
    public String featureProduct(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        // If not exists, throw exception
        if (!optionalProduct.isPresent()) {
            throw new ProductNotFoundException("The product that you want to feature does not exist");
        }

        Product product = optionalProduct.get();
        boolean wasFeatured = product.getFeatured();
        product.setFeatured(!wasFeatured); // Toggle the featured status
        productRepository.save(product);

        return wasFeatured ? "Product has been unfeatured." : "Product has been featured.";
    }
}