package com.github.faening.engsofttmdb.data.model.db

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "author_details")
data class AuthorDetailsEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,

    @Column(name = "name", nullable = false, length = 100)
    val name: String,

    @Column(name = "username", nullable = false, length = 100)
    val username: String,

    @Column(name = "avatar_path", nullable = true, length = 100)
    val avatarPath: String?,

    @Column(name = "rating", nullable = true, columnDefinition = "DEFAULT 0")
    val rating: Int?, // TODO: Analisar se é necessário ajustar para Double

    @OneToMany(mappedBy = "author", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var reviews: MutableList<ReviewEntity>? = mutableListOf(),

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "createdAt", column = Column(name = "created_at")),
        AttributeOverride(name = "updatedAt", column = Column(name = "updated_at"))
    )
    val metadata: MetadataEntity?
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AuthorDetailsEntity

        if (id != other.id) return false
        if (name != other.name) return false
        if (username != other.username) return false
        if (avatarPath != other.avatarPath) return false
        if (rating != other.rating) return false
        if (metadata != other.metadata) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + username.hashCode()
        result = 31 * result + (avatarPath?.hashCode() ?: 0)
        result = 31 * result + (rating ?: 0)
        result = 31 * result + (metadata?.hashCode() ?: 0)
        return result
    }
}