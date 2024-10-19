package com.app.jagarv.repository;

import com.app.jagarv.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {
    
}
