package com.app.jagarv.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.app.jagarv.entity.user.Ban;

public interface BanRepository extends JpaRepository<Ban, Long> {
    Optional<Ban> findByUserId(Long userId);
    Boolean existsByUserId(Long userId);
}