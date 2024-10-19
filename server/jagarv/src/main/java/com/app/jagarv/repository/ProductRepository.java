package com.app.jagarv.repository;

import com.app.jagarv.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
   boolean existsByTitle(String title);
}