package com.kiki.newsbe.repositories.auth;

import com.kiki.newsbe.repositories.entities.auth.RoleEntity;
import com.kiki.newsbe.utils.enumaration.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {
    Optional<RoleEntity> findByName(RoleEnum name);
}
