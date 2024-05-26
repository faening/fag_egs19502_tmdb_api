package com.github.faening.engsofttmdb.domain.model

import java.io.Serializable
import java.time.LocalDateTime

data class Genre(
    val id: Long? = null,
    val tmdbId: Int? = null,
    val name: String,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
) : Serializable