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

    @Column(name = "content", nullable = false, length = 1000)
    val content: String,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    val author: AuthorDetailsEntity,

    @Column(name = "created_at", nullable = false, length = 100)
    val createdAt: String,

    @Column(name = "tmdb_author_id", nullable = false, length = 100)
    val tmdbAuthorId: String,

    @Column(name = "updated_at", nullable = false, length = 100)
    val updatedAt: String,

    @Column(name = "url", nullable = false, length = 100)
    val url: String
) : Serializable