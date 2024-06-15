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

    @Column(name = "author", nullable = false, length = 100)
    val author: String,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_details_id")
    var authorDetailsId: AuthorDetailsEntity?,

    @Column(name = "content", nullable = false, length = 1000)
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
) : Serializable