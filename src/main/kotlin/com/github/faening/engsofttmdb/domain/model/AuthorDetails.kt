package com.github.faening.engsofttmdb.domain.model

import java.io.Serializable
import java.time.LocalDateTime

data class AuthorDetails(
    val id: Long?,
    val name: String,
    val username: String,
    val avatarPath: String?,
    val rating: Int?,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AuthorDetails

        if (id != other.id) return false
        if (name != other.name) return false
        if (username != other.username) return false
        if (avatarPath != other.avatarPath) return false
        if (rating != other.rating) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + username.hashCode()
        result = 31 * result + (avatarPath?.hashCode() ?: 0)
        result = 31 * result + (rating ?: 0)
        result = 31 * result + (createdAt?.hashCode() ?: 0)
        result = 31 * result + (updatedAt?.hashCode() ?: 0)
        return result
    }

}