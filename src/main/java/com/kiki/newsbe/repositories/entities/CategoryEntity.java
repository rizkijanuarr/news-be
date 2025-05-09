package com.kiki.newsbe.repositories.entities;

import com.kiki.newsbe.repositories.entities.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "categories")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    @Column(name = "slug", unique = true, nullable = false)
    @NotNull
    private String slug;

    @Column(name = "image", nullable = false)
    @NotNull
    private String image;

}
