package com.app.jagarv.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import com.app.jagarv.entity.product.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
   boolean existsByTitle(String title);

   @EntityGraph(attributePaths = 
   {"category", "variants", "variants.attributeOptions", "category.attributes"})
   Optional<Product> findById(Long id);
   
}