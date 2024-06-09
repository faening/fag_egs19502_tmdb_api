package com.github.faening.engsofttmdb.domain.model

import java.io.Serializable
import java.time.LocalDateTime

data class Cast(
    val id: Long? = null,
    val adult: Boolean? = null,
    val gender: Int = 0,
    val tmdbId: Long = 0,
    val knownForDepartment: String = "",
    val name: String = "",
    val originalName: String = "",
    val popularity: Double? = 0.0,
    val profilePath: String? = "",
    val castId: Int? = null,
    val character: String = "",
    val creditId: String? = "",
    val order: Int? = null,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cast

        if (id != other.id) return false
        if (adult != other.adult) return false
        if (gender != other.gender) return false
        if (tmdbId != other.tmdbId) return false
        if (knownForDepartment != other.knownForDepartment) return false
        if (name != other.name) return false
        if (originalName != other.originalName) return false
        if (popularity != other.popularity) return false
        if (profilePath != other.profilePath) return false
        if (castId != other.castId) return false
        if (character != other.character) return false
        if (creditId != other.creditId) return false
        if (order != other.order) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (adult?.hashCode() ?: 0)
        result = 31 * result + gender
        result = 31 * result + tmdbId.hashCode()
        result = 31 * result + knownForDepartment.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + originalName.hashCode()
        result = 31 * result + (popularity?.hashCode() ?: 0)
        result = 31 * result + (profilePath?.hashCode() ?: 0)
        result = 31 * result + (castId ?: 0)
        result = 31 * result + character.hashCode()
        result = 31 * result + (creditId?.hashCode() ?: 0)
        result = 31 * result + (order ?: 0)
        return result
    }

}