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

    @Column(name = "tmdb_id", nullable = false)
    val tmdbId: Long,

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

    @ManyToMany(mappedBy = "crews", fetch = FetchType.EAGER)
    val movies: MutableSet<MovieEntity>? = mutableSetOf(),

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

        other as CrewEntity

        if (id != other.id) return false
        if (adult != other.adult) return false
        if (gender != other.gender) return false
        if (tmdbId != other.tmdbId) return false
        if (knownForDepartment != other.knownForDepartment) return false
        if (name != other.name) return false
        if (originalName != other.originalName) return false
        if (popularity != other.popularity) return false
        if (profilePath != other.profilePath) return false
        if (creditId != other.creditId) return false
        if (department != other.department) return false
        if (job != other.job) return false
        if (metadata != other.metadata) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (adult?.hashCode() ?: 0)
        result = 31 * result + gender
        result = 31 * result + tmdbId.hashCode()
        result = 31 * result + knownForDepartment.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + originalName.hashCode()
        result = 31 * result + (popularity?.hashCode() ?: 0)
        result = 31 * result + (profilePath?.hashCode() ?: 0)
        result = 31 * result + (creditId?.hashCode() ?: 0)
        result = 31 * result + (department?.hashCode() ?: 0)
        result = 31 * result + (job?.hashCode() ?: 0)
        result = 31 * result + (metadata?.hashCode() ?: 0)
        return result
    }

}