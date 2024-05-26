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

    @ManyToMany(mappedBy = "genres")
    val movies: Set<MovieEntity> = HashSet(),

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "createdAt", column = Column(name = "created_at")),
        AttributeOverride(name = "updatedAt", column = Column(name = "updated_at"))
    )
    val metadata: MetadataEntity?
) : Serializable