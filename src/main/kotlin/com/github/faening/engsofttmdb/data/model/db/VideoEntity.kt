package com.github.faening.engsofttmdb.data.model.db

import jakarta.persistence.*
import java.io.Serializable

@Suppress("unused")
@Entity
@Table(name = "video")
data class VideoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id")
    var movie: MovieEntity?,

    @Column(name = "iso_639_1", length = 2, columnDefinition = "DEFAULT 'pt'")
    val iso6391: String? = "pt",

    @Column(name = "iso_3166_1", length = 2, columnDefinition = "DEFAULT 'BR'")
    val iso31661: String? = "BR",

    @Column(name = "name", nullable = false, length = 255)
    val name: String,

    @Column(name = "video_key", nullable = false, length = 80)
    val videoKey: String,

    @Column(name = "site", columnDefinition = "DEFAULT 'YouTube'")
    val site: String? = "YouTube",

    @Column(name = "size", columnDefinition = "DEFAULT 1080")
    val size: Int? = 1080,

    @Column(name = "type")
    val type: String?,

    @Column(name = "official", columnDefinition = "DEFAULT false")
    val official: Boolean? = false,

    @Column(name = "published_at")
    val publishedAt: String?,

    @Column(name = "tmdb_id")
    val tmdbId: String?,

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "createdAt", column = Column(name = "created_at")),
        AttributeOverride(name = "updatedAt", column = Column(name = "updated_at"))
    )
    val metadata: MetadataEntity?
) : Serializable