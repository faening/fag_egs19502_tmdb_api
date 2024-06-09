package com.github.faening.engsofttmdb.domain.model

import com.fasterxml.jackson.annotation.JsonBackReference
import java.io.Serializable
import java.time.LocalDateTime

data class Crew(
    val id: Long? = null,
    val adult: Boolean? = null,
    val gender: Int = 0,
    val tmdbId: Long = 0,
    val knownForDepartment: String = "",
    val name: String = "",
    val originalName: String = "",
    val popularity: Double? = null,
    val profilePath: String? = null,
    val creditId: String? = null,
    val department: String? = null,
    val job: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Crew

        if (id != other.id) return false
        if (adult != other.adult) return false
        if (gender != other.gender) return false
        if (tmdbId != other.tmdbId) return false
        if (knownForDepartment != other.knownForDepartment) return false
        if (name != other.name) return false
        if (originalName != other.originalName) return false
        if (popularity != other.popularity) return false
        if (profilePath != other.profilePath) return false
        if (creditId != other.creditId) return false
        if (department != other.department) return false
        if (job != other.job) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false

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
        result = 31 * result + (creditId?.hashCode() ?: 0)
        result = 31 * result + (department?.hashCode() ?: 0)
        result = 31 * result + (job?.hashCode() ?: 0)
        result = 31 * result + (createdAt?.hashCode() ?: 0)
        result = 31 * result + (updatedAt?.hashCode() ?: 0)
        return result
    }

}