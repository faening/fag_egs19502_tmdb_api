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
    val rating: Int?, // TODO: Analisar se é necessário ajustar para Double

    @OneToMany(mappedBy = "author", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var reviews: MutableList<ReviewEntity>? = mutableListOf(),

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "createdAt", column = Column(name = "created_at")),
        AttributeOverride(name = "updatedAt", column = Column(name = "updated_at"))
    )
    val metadata: MetadataEntity?
) : Serializable