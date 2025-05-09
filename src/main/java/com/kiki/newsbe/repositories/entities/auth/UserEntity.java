package com.kiki.newsbe.repositories.entities.auth;

import com.kiki.newsbe.repositories.entities.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity {

    @Column(name = "user_name", nullable = false)
    @NotNull
    private String user_name;

    @Column(name = "user_email", unique = true, nullable = false)
    @NotNull
    @JsonProperty("userEmail")
    private String user_email;

    @Column(name = "user_password", nullable = false)
    @NotNull
    private String user_password;

    @Column(name = "user_phone", nullable = true)
    private String user_phone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

}

