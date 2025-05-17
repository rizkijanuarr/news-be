package com.kiki.newsbe.repositories;

import com.kiki.newsbe.repositories.entities.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, String> {

    @Query("SELECT p FROM PostEntity p " +
            "WHERE p.active = true " +
            "AND (:stringFilter is null OR p.title like %:stringFilter%) " +
            "ORDER BY p.createdDate DESC")
    Page<PostEntity> findAllByActiveTrueOrderByCreatedDateDesc(
            Pageable pageable,
            @Param("stringFilter") String stringFilter
    );

    Slice<PostEntity> findAllByActiveFalseOrderByCreatedDateDesc(Pageable pageable);

    @Query("SELECT COUNT(p) FROM PostEntity p WHERE p.active = true")
    Integer countActivePosts();
}
