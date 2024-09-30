package com.michael.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@MappedSuperclass
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class AuditTrail {
    @CreationTimestamp
    @Column(name = "created_at")
    @JsonIgnore
    protected Timestamp createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    @JsonIgnore
    protected Timestamp updatedAt;
    @Column(name = "deleted_at")
    @JsonIgnore
    protected Timestamp deletedAt;
}
