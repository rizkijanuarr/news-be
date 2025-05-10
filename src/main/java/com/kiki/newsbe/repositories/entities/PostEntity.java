package com.kiki.newsbe.repositories.entities;

import com.kiki.newsbe.repositories.entities.auth.UserEntity;
import com.kiki.newsbe.repositories.entities.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "posts")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user_id;

    @Column(name = "title", nullable = false)
    @NotNull
    private String title;

    @Column(name = "slug", unique = true, nullable = false)
    @NotNull
    private String slug;

    @Column(name = "content", nullable = false)
    @NotNull
    private String content;

    @Column(name = "image", nullable = false)
    @NotNull
    private String image;

}
