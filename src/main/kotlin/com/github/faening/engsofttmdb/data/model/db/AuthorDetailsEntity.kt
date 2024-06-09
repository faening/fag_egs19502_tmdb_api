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
    val rating: Int?,

    @OneToMany(mappedBy = "author", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val reviews: MutableList<ReviewEntity> = mutableListOf()
) : Serializable