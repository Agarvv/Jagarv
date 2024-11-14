package com.app.jagarv.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.jagarv.entity.user.User;



public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username); 
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
