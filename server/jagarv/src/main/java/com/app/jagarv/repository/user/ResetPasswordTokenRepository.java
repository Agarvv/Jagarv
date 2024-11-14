package com.app.jagarv.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.jagarv.entity.user.ResetPasswordToken;

import java.util.Optional;

public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, Long> {
    
    Optional<ResetPasswordToken> findByToken(String token);
    
    void deleteByUserEmail(String email);
    
    Optional<ResetPasswordToken> findByUserEmailAndToken(String userEmail, String token);
}
