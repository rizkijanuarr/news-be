package com.kiki.newsbe.repositories.entities;

import com.kiki.newsbe.repositories.entities.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "post_views")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostViewsEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post_id;

    @Column(name = "views", nullable = false)
    @NotNull
    private Integer views;

}