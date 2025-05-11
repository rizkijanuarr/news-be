package com.kiki.newsbe.repositories;

import com.kiki.newsbe.repositories.entities.PostViewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface PostViewsRepository extends JpaRepository<PostViewsEntity, String> {

    @Query(value = "SELECT COUNT(id) as count, DATE(created_date) as day " +
            "FROM post_views " +
            "WHERE created_date >= :date " +
            "GROUP BY DATE(created_date)",
            nativeQuery = true)
    List<Map<String, Object>> findPostViewsGroupedByDay(@Param("date") Date date);

}
