package com.app.jagarv.repository;

import com.app.jagarv.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username); 
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
