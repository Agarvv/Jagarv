package com.app.jagarv.repository;

import com.app.jagarv.entity.ResetPasswordToken;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, Long> {
    
    Optional<ResetPasswordToken> findByToken(String token);
    
    void deleteByUserId(Long userId);
    
    Optional<ResetPasswordToken> findByUserEmailAndToken(String userEmail, String token);
}
