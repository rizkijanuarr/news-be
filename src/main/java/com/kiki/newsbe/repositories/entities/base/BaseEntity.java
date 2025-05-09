package com.kiki.newsbe.repositories.entities.base;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class BaseEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "active", columnDefinition = "TINYINT default 1")
    @Convert(converter = org.hibernate.type.NumericBooleanConverter.class)
    @NotNull
    private Boolean active;

    @Column(name = "created_date")
    @NotNull
    private Date createdDate;

    @Column(name = "modified_date")
    private Date modifiedDate;

    @Column(name = "deleted_date")
    private Date deletedDate;

    @Column(name = "created_by")
    @NotNull
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "deleted_by")
    private String deletedBy;

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID().toString();
        this.active = true;
        this.createdDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.modifiedDate = new Date();
    }

    @PreRemove
    public void preRemove() {
        this.deletedDate = new Date();
        this.active = false;
    }
}
