package com.kiki.newsbe.repositories.auth;

import com.kiki.newsbe.repositories.entities.auth.RefreshTokenEntity;
import com.kiki.newsbe.repositories.entities.auth.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, String> {
    Optional<RefreshTokenEntity> findByToken(String token);
    
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM RefreshTokenEntity r WHERE r.user = :user")
    int deleteByUser(UserEntity user);
    
    Optional<RefreshTokenEntity> findByUser(UserEntity user);
} 