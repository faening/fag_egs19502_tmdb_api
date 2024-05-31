package com.github.faening.engsofttmdb.data.model.db

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import java.io.Serializable

@Suppress("unused")
@Entity
@Table(name = "crew")
data class CrewEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,

    @Column(name = "adult", nullable = true, columnDefinition = "DEFAULT FALSE")
    val adult: Boolean? = false,

    @Column(name = "gender", nullable = false, columnDefinition = "DEFAULT 0")
    @Min(0)
    @Max(2)
    val gender: Int = 0,

    @Column(name = "known_for_department", nullable = false, length = 100)
    val knownForDepartment: String = "",

    @Column(name = "name", nullable = false, length = 100)
    val name: String = "",

    @Column(name = "original_name", nullable = false, length = 100)
    val originalName: String = "",

    @Column(name = "popularity", nullable = true, columnDefinition = "DEFAULT 0.0")
    val popularity: Double? = 0.0,

    @Column(name = "profile_path", nullable = true, length = 100)
    val profilePath: String? = "",

    @Column(name = "credit_id", nullable = true, length = 100)
    val creditId: String? = "",

    @Column(name = "department", nullable = true, length = 100)
    val department: String? = "",

    @Column(name = "job", nullable = true, length = 100)
    val job: String? = "",

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "createdAt", column = Column(name = "created_at")),
        AttributeOverride(name = "updatedAt", column = Column(name = "updated_at"))
    )
    val metadata: MetadataEntity?
) : Serializable