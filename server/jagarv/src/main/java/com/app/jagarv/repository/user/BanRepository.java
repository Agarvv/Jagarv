package com.app.jagarv.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.jagarv.entity.user.Ban; 


public interface BanRepository extends JpaRepository<Ban, Long> {
    
}