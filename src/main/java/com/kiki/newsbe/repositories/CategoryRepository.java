package com.kiki.newsbe.repositories;

import com.kiki.newsbe.repositories.entities.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {

    List<CategoryEntity> findAllByOrderByCreatedDateDesc();


    @Query("SELECT c FROM CategoryEntity c " +
            "WHERE c.active = true " +
            "AND (:stringFilter is null OR c.name like %:stringFilter%) " +
            "ORDER BY c.createdDate DESC")
    Page<CategoryEntity> findAllByActiveTrueOrderByCreatedDateDesc(
            Pageable pageable,
            @Param("stringFilter") String stringFilter
    );
    Slice<CategoryEntity> findAllByActiveFalseOrderByCreatedDateDesc(Pageable pageable);

    @Query("SELECT COUNT(c) FROM CategoryEntity c WHERE c.active = true")
    Integer countActiveCategories();

}