package com.github.faening.engsofttmdb.data.model.db

import jakarta.persistence.*
import java.io.Serializable

@Suppress("unused")
@Entity
@Table(name = "genre")
data class GenreEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,

    @Column(name = "tmdb_id", columnDefinition = "DEFAULT NULL")
    val tmdbId: Int?,

    @Column(name = "name", nullable = false, length = 100)
    val name: String,

    @ManyToMany(mappedBy = "genres", fetch = FetchType.EAGER)
    val movies: Set<MovieEntity>,

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

        other as GenreEntity

        if (id != other.id) return false
        if (tmdbId != other.tmdbId) return false
        if (name != other.name) return false
        if (metadata != other.metadata) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (tmdbId ?: 0)
        result = 31 * result + name.hashCode()
        result = 31 * result + (metadata?.hashCode() ?: 0)
        return result
    }
}