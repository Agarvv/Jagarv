package com.app.jagarv.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.jagarv.entity.user.User;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username); 
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    
    @Query(value = "SELECT COUNT(*) FROM app_user WHERE DATE(joined_at) = CURRENT_DATE", nativeQuery = true)
    Long countUsersToday();
}
