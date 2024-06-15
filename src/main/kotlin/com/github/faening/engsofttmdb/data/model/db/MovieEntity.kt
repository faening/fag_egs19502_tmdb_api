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
    val tmdbId: Long?,

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
    var genres: MutableSet<GenreEntity>,

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
    var casts: MutableSet<CastEntity>? = mutableSetOf(),

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinTable(
        name = "movie_crew",
        joinColumns = [JoinColumn(name = "movie_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "crew_id", referencedColumnName = "id")]
    )
    var crews: MutableSet<CrewEntity>? = mutableSetOf(),

    @OneToMany(mappedBy = "movie", cascade = [CascadeType.ALL])
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

        other as MovieEntity

        if (id != other.id) return false
        if (tmdbId != other.tmdbId) return false
        if (adult != other.adult) return false
        if (backdropPath != other.backdropPath) return false
        if (originalLanguage != other.originalLanguage) return false
        if (originalTitle != other.originalTitle) return false
        if (overview != other.overview) return false
        if (popularity != other.popularity) return false
        if (posterPath != other.posterPath) return false
        if (releaseDate != other.releaseDate) return false
        if (title != other.title) return false
        if (video != other.video) return false
        if (voteAverage != other.voteAverage) return false
        if (voteCount != other.voteCount) return false
        if (metadata != other.metadata) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (tmdbId?.hashCode() ?: 0)
        result = 31 * result + adult.hashCode()
        result = 31 * result + backdropPath.hashCode()
        result = 31 * result + originalLanguage.hashCode()
        result = 31 * result + originalTitle.hashCode()
        result = 31 * result + overview.hashCode()
        result = 31 * result + popularity.hashCode()
        result = 31 * result + posterPath.hashCode()
        result = 31 * result + releaseDate.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + video.hashCode()
        result = 31 * result + voteAverage.hashCode()
        result = 31 * result + voteCount
        result = 31 * result + (metadata?.hashCode() ?: 0)
        return result
    }
}