package com.kiki.newsbe.repositories;

import com.kiki.newsbe.repositories.entities.PostEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, String> {

    Slice<PostEntity> findAllByActiveTrueOrderByCreatedDateDesc(Pageable pageable);
    Slice<PostEntity> findAllByActiveFalseOrderByCreatedDateDesc(Pageable pageable);
}
