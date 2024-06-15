package com.github.faening.engsofttmdb.domain.model

import java.io.Serializable
import java.time.LocalDateTime

data class Review(
    val id: Long?,
    val author: String,
    var authorDetails: AuthorDetails?,
    val content: String,
    val tmdbId: String?,
    val url: String?,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Review

        if (id != other.id) return false
        if (author != other.author) return false
        if (content != other.content) return false
        if (tmdbId != other.tmdbId) return false
        if (url != other.url) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + author.hashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + (tmdbId?.hashCode() ?: 0)
        result = 31 * result + (url?.hashCode() ?: 0)
        result = 31 * result + (createdAt?.hashCode() ?: 0)
        result = 31 * result + (updatedAt?.hashCode() ?: 0)
        return result
    }
}
