package com.github.faening.engsofttmdb.data.model.db

import jakarta.persistence.*
import java.io.Serializable

@Suppress("unused")
@Entity
@Table(name = "movie")
data class MovieEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,

    @Column(name = "adult", columnDefinition = "BOOLEAN DEFAULT FALSE")
    val adult: Boolean,

    @Column(name = "backdrop_path", nullable = false, length = 1000)
    val backdropPath: String,

    @Column(name = "genre_ids", nullable = false, length = 1000)
    val genreIds: List<Int>,

    @Column(name = "id_tmdb", columnDefinition = "BOOLEAN DEFAULT NULL")
    val idTmdb: Int?,

    @Column(name = "original_language", length = 10)
    val originalLanguage: String,

    @Column(name = "original_title", length = 255)
    val originalTitle: String,

    @Column(name = "overview", length = 1000)
    val overview: String,

    @Column(name = "popularity")
    val popularity: Double,

    @Column(name = "poster_path", nullable = false, length = 1000)
    val posterPath: String,

    @Column(name = "release_date", nullable = false, length = 10)
    val releaseDate: String,

    @Column(name = "title", nullable = false, length = 255)
    val title: String,

    @Column(name = "video", columnDefinition = "BOOLEAN DEFAULT FALSE")
    val video: Boolean,

    @Column(name = "vote_average")
    val voteAverage: Double,

    @Column(name = "vote_count")
    val voteCount: Int,

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "createdAt", column = Column(name = "created_at")),
        AttributeOverride(name = "updatedAt", column = Column(name = "updated_at"))
    )
    val metadata: MetadataEntity?
) : Serializable
