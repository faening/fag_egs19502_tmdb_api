package com.github.faening.engsofttmdb.domain.model

import java.io.Serializable
import java.time.LocalDateTime

data class Video(
    val id: Long?,
    val movieId: Long?,
    val iso6391: String?,
    val iso31661: String?,
    val name: String,
    val videoKey: String,
    val site: String?,
    val size: Int?,
    val type: String?,
    val official: Boolean?,
    val publishedAt: String?,
    val tmdbId: String?,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Video

        if (id != other.id) return false
        if (iso6391 != other.iso6391) return false
        if (iso31661 != other.iso31661) return false
        if (name != other.name) return false
        if (videoKey != other.videoKey) return false
        if (site != other.site) return false
        if (size != other.size) return false
        if (type != other.type) return false
        if (official != other.official) return false
        if (publishedAt != other.publishedAt) return false
        if (tmdbId != other.tmdbId) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (iso6391?.hashCode() ?: 0)
        result = 31 * result + (iso31661?.hashCode() ?: 0)
        result = 31 * result + name.hashCode()
        result = 31 * result + videoKey.hashCode()
        result = 31 * result + (site?.hashCode() ?: 0)
        result = 31 * result + (size ?: 0)
        result = 31 * result + (type?.hashCode() ?: 0)
        result = 31 * result + (official?.hashCode() ?: 0)
        result = 31 * result + (publishedAt?.hashCode() ?: 0)
        result = 31 * result + (tmdbId?.hashCode() ?: 0)
        result = 31 * result + (createdAt?.hashCode() ?: 0)
        result = 31 * result + (updatedAt?.hashCode() ?: 0)
        return result
    }

}