package com.app.jagarv.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.jagarv.entity.user.User;



public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username); 
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    
    
   // @Query("SELECT COUNT(u) FROM User u WHERE u.joinedAt = :today")
   // Long getUserRegistrationsToday(@Param("today") String today);

}
