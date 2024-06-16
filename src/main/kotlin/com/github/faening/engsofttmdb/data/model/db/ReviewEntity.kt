package com.github.faening.engsofttmdb.data.model.db

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "review")
data class ReviewEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id")
    var movie: MovieEntity?,

    @Column(name = "author", nullable = false, length = 100)
    val author: String,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_details_id")
    var authorDetails: AuthorDetailsEntity?,

    @Column(name = "content", nullable = false)
    val content: String,

    @Column(name = "tmdb_id", length = 100)
    val tmdbId: String?,

    @Column(name = "url", length = 100)
    val url: String?,

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

        other as ReviewEntity

        if (id != other.id) return false
        if (author != other.author) return false
        if (content != other.content) return false
        if (tmdbId != other.tmdbId) return false
        if (url != other.url) return false
        if (metadata != other.metadata) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + author.hashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + (tmdbId?.hashCode() ?: 0)
        result = 31 * result + (url?.hashCode() ?: 0)
        result = 31 * result + (metadata?.hashCode() ?: 0)
        return result
    }
}