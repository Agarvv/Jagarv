package com.app.jagarv.service.products;

import org.springframework.stereotype.Service;

import com.app.jagarv.repository.product.ProductRepository;
import com.app.jagarv.repository.product.ProductVariantRepository;
import com.app.jagarv.repository.product.AttributeOptionRepository;
import com.app.jagarv.exception.exceptions.products.ProductNotFoundException;
import com.app.jagarv.dto.product.create.CreateProductVariantDTO;
import com.app.jagarv.entity.product.AttributeOption;
import com.app.jagarv.entity.product.ProductVariant;
import com.app.jagarv.entity.product.Product;
import java.util.List;

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

    public void createProductVariant(CreateProductVariantDTO variant) {
        Product product = productRepository.findById(variant.getProductId())
            .orElseThrow(() -> new ProductNotFoundException("Product not exists, please try with another"));

        List<AttributeOption> options = attributeOptionRepository.findAllById(variant.getOptionIds());

        ProductVariant newVariant = new ProductVariant();
        newVariant.setProduct(product);
        newVariant.setPrice(variant.getPrice());
        newVariant.setStock(variant.getStock());
        newVariant.setImages(variant.getImages());
        newVariant.setAttributeOptions(options);

        productVariantRepository.save(newVariant);
    }
}
