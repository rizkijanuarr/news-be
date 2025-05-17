package com.kiki.newsbe.repositories.entities.auth;

import com.kiki.newsbe.repositories.entities.base.BaseEntity;
import com.kiki.newsbe.utils.enumaration.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity extends BaseEntity {

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

}
