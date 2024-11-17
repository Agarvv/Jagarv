package com.app.jagarv.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.jagarv.entity.product.Product;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
   
   boolean existsByTitle(String title);

   @EntityGraph(attributePaths = 
   {"category", "category.attributes", "variants", "variants.attributeOptions"})
   Optional<Product> findById(Long id);
   
   List<Product> findByCategory_Name(String categoryName); 

   @Query("SELECT p FROM products p WHERE p.title LIKE %:title%")
   List<Product> findProductsByTitle(@Param("title") String title);
}