package com.app.jagarv.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// import java.util.List;

import com.app.jagarv.entity.product.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
   boolean existsByTitle(String title);
}