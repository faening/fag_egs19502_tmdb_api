package com.github.faening.engsofttmdb.data.model.db

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.annotations.CreationTimestamp
import java.io.Serializable
import java.time.LocalDateTime

@Suppress("unused")
@Embeddable
data class MetadataEntity(
    @Column(name = "created_at", updatable = false, nullable = false)
    @CreationTimestamp
    val createdAt: LocalDateTime,

    @Column(name = "updated_at", nullable = false)
    @CreationTimestamp
    val updatedAt: LocalDateTime
) : Serializable
