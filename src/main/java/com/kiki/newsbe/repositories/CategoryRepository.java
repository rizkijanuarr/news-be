package com.kiki.newsbe.repositories;

import com.kiki.newsbe.repositories.entities.CategoryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {

    List<CategoryEntity> findAllByOrderByCreatedDateDesc();
    Slice<CategoryEntity> findAllByActiveTrueOrderByCreatedDateDesc(Pageable pageable);
    Slice<CategoryEntity> findAllByActiveFalseOrderByCreatedDateDesc(Pageable pageable);

    @Query("SELECT COUNT(c) FROM CategoryEntity c WHERE c.active = true")
    Integer countActiveCategories();

}