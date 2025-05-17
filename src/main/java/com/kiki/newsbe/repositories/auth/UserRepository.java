package com.kiki.newsbe.repositories.auth;

import com.kiki.newsbe.repositories.entities.auth.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    List<UserEntity> findAllByOrderByCreatedDateDesc();

    @Query("SELECT u FROM UserEntity u " +
            "WHERE u.active = true " +
            "AND (:stringFilter is null OR u.user_name like %:stringFilter%) " +
            "ORDER BY u.createdDate DESC")
    Page<UserEntity> findAllByActiveTrueOrderByCreatedDateDesc(
            Pageable pageable,
            @Param("stringFilter") String stringFilter
    );

    Slice<UserEntity> findAllByActiveFalseOrderByCreatedDateDesc(Pageable pageable);

    @Query(
            value = "select * from user where user_email = ? and deleted_by IS NULL",
            nativeQuery = true
    )
    Optional<UserEntity> login(String email);

    @Query("SELECT u FROM UserEntity u WHERE u.user_email = :email")
    Optional<UserEntity> findByUser_email(String email);

    @Query("SELECT COUNT(u) FROM UserEntity u WHERE u.active = true")
    Integer countActiveUsers();
}
