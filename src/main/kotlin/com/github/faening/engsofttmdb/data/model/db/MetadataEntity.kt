package com.github.faening.engsofttmdb.data.model.db

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.io.Serializable
import java.time.LocalDateTime

@Suppress("unused")
@Embeddable
data class MetadataEntity(
    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    val createdAt: LocalDateTime?,

    @Column(name = "updated_at")
    @CreationTimestamp
    val updatedAt: LocalDateTime?
) : Serializable {

    companion object {
        @Suppress("ConstPropertyName")
        private const val serialVersionUID = 1L
    }

}