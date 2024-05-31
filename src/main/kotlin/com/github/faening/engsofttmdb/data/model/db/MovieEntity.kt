package com.github.faening.engsofttmdb.data.model.db

import jakarta.persistence.*
import java.io.Serializable
import java.time.LocalDate

@Suppress("unused")
@Entity
@Table(name = "movie")
data class MovieEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,

    @Column(name = "tmdb_id", columnDefinition = "DEFAULT NULL")
    val tmdbId: Int?,

    @Column(name = "adult", columnDefinition = "BOOLEAN DEFAULT FALSE")
    val adult: Boolean,

    @Column(name = "backdrop_path", nullable = false, length = 255)
    val backdropPath: String,

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinTable(
        name = "movie_genre",
        joinColumns = [JoinColumn(name = "movie_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "genre_id", referencedColumnName = "id")]
    )
    var genres: Set<GenreEntity> = HashSet(),

    @Column(name = "original_language", length = 10, columnDefinition = "DEFAULT NULL")
    val originalLanguage: String,

    @Column(name = "original_title", length = 100, columnDefinition = "DEFAULT NULL")
    val originalTitle: String,

    @Column(name = "overview", length = 1000, columnDefinition = "DEFAULT NULL")
    val overview: String,

    @Column(name = "popularity", columnDefinition = "DEFAULT NULL")
    val popularity: Double,

    @Column(name = "poster_path", nullable = false, length = 255)
    val posterPath: String,

    @Column(name = "release_date", nullable = false)
    val releaseDate: LocalDate,

    @Column(name = "title", nullable = false, length = 100)
    val title: String,

    @Column(name = "video", columnDefinition = "BOOLEAN DEFAULT FALSE")
    val video: Boolean,

    @Column(name = "vote_average", columnDefinition = "DEFAULT NULL")
    val voteAverage: Double,

    @Column(name = "vote_count", columnDefinition = "DEFAULT NULL")
    val voteCount: Int,

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinTable(
        name = "movie_cast",
        joinColumns = [JoinColumn(name = "movie_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "cast_id", referencedColumnName = "id")]
    )
    val cast: Set<CastEntity> = HashSet(),

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinTable(
        name = "movie_crew",
        joinColumns = [JoinColumn(name = "movie_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "crew_id", referencedColumnName = "id")]
    )
    val crew: Set<CrewEntity> = HashSet(),

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "createdAt", column = Column(name = "created_at")),
        AttributeOverride(name = "updatedAt", column = Column(name = "updated_at"))
    )
    val metadata: MetadataEntity?
) : Serializable
