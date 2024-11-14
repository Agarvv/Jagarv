package com.app.jagarv.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import com.app.jagarv.entity.product.Product;
import java.util.list;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
   
   boolean existsByTitle(String title);

   @EntityGraph(attributePaths = 
   {"category", "category.attributes", "variants", "variants.attributeOptions"})
   Optional<Product> findById(Long id);
   
   List<Product> findByCategory_Name(String categoryName); 
}