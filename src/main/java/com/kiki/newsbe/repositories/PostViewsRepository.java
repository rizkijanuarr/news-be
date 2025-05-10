package com.kiki.newsbe.repositories;

import com.kiki.newsbe.repositories.entities.PostViewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostViewsRepository extends JpaRepository<PostViewsEntity, String> {

}
