package com.ecommerce.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Base abstract class for all entities in the system.
 * Provides automated auditing fields for creation and update timestamps.
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Timestamp of record creation. Not updatable after initial persistence.
     */
    @Column(updatable = false)
    private LocalDateTime createdAt;

    /**
     * Timestamp of the last record update.
     */
    private LocalDateTime updatedAt;

    /**
     * Hook executed before the entity is persisted for the first time.
     */
    @PrePersist
    protected void onPrePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Hook executed before every update to the entity.
     */
    @PreUpdate
    protected void onPreUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}