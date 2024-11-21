package com.app.jagarv.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.jagarv.entity.user.Ban;

import org.springframework.stereotype.Repository;

@Repository
public interface BanRepository extends JpaRepository<Ban, Long> {

    Ban findByUserId(Long userId);
    Boolean existsByUserId(Long userId);
}