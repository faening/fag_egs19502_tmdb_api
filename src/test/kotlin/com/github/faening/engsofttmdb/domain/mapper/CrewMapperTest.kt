package com.github.faening.engsofttmdb.domain.mapper

import com.github.faening.engsofttmdb.data.model.api.credits.CrewApiData
import com.github.faening.engsofttmdb.data.model.db.CrewEntity
import com.github.faening.engsofttmdb.data.model.db.MetadataEntity
import com.github.faening.engsofttmdb.domain.model.Crew
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime

@SpringBootTest
@ActiveProfiles("test")
class CrewMapperTest {

    private lateinit var apiData: CrewApiData
    private lateinit var entity: CrewEntity
    private lateinit var domain: Crew

    private lateinit var mapper: CrewMapper

    @BeforeEach
    fun setup() {
        apiData = CrewApiData(
            adult = false,
            gender = 0,
            id = 1,
            knownForDepartment = "Acting",
            name = "author_details_1",
            originalName = "originalName",
            popularity = 5.0,
            profilePath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
            creditId = "creditId",
            department = "department",
            job = "job"
        )

        entity = CrewEntity(
            id = 1,
            adult = false,
            gender = 0,
            tmdbId = 1,
            knownForDepartment = "Acting",
            name = "author_details_1",
            originalName = "originalName",
            popularity = 5.0,
            profilePath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
            creditId = "creditId",
            department = "department",
            job = "job",
            metadata = MetadataEntity(
                createdAt = LocalDateTime.parse("2024-06-16T10:00:00"),
                updatedAt = LocalDateTime.parse("2024-06-16T10:00:00")
            )
        )

        domain = Crew(
            id = 1,
            adult = false,
            gender = 0,
            tmdbId = 1,
            knownForDepartment = "Acting",
            name = "author_details_1",
            originalName = "originalName",
            popularity = 5.0,
            profilePath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
            creditId = "creditId",
            department = "department",
            job = "job",
            createdAt = LocalDateTime.parse("2024-06-16T10:00:00"),
            updatedAt = LocalDateTime.parse("2024-06-16T10:00:00")
        )

        mapper = CrewMapper()
    }

    @Test
    @DisplayName("Deve mapear `CrewApiData` para `CrewEntity` com sucesso")
    fun fromApiDataToEntity_ShouldReturnCrewEntity_WithCorrectValues() {
        val result = mapper.fromApiDataToEntity(apiData)

        assertEquals(entity.adult, result.adult)
        assertEquals(entity.gender, result.gender)
        assertEquals(entity.tmdbId, result.tmdbId)
        assertEquals(entity.knownForDepartment, result.knownForDepartment)
        assertEquals(entity.name, result.name)
        assertEquals(entity.originalName, result.originalName)
        assertEquals(entity.popularity, result.popularity)
        assertEquals(entity.profilePath, result.profilePath)
        assertEquals(entity.creditId, result.creditId)
        assertEquals(entity.department, result.department)
        assertEquals(entity.job, result.job)
        assert(result.metadata?.createdAt != entity.metadata?.createdAt)
        assert(result.metadata?.updatedAt != entity.metadata?.updatedAt)
    }

    @Test
    @DisplayName("Deve mapear `CrewEntity` para `Crew` com sucesso")
    fun fromEntityToDomain_ShouldReturnCrew_WithCorrectValues() {
        val result = mapper.fromEntityToDomain(entity)

        assertEquals(domain.id, result.id)
        assertEquals(domain.adult, result.adult)
        assertEquals(domain.gender, result.gender)
        assertEquals(domain.tmdbId, result.tmdbId)
        assertEquals(domain.knownForDepartment, result.knownForDepartment)
        assertEquals(domain.name, result.name)
        assertEquals(domain.originalName, result.originalName)
        assertEquals(domain.popularity, result.popularity)
        assertEquals(domain.profilePath, result.profilePath)
        assertEquals(domain.creditId, result.creditId)
        assertEquals(domain.department, result.department)
        assertEquals(domain.job, result.job)
        assertEquals(domain.createdAt, result.createdAt)
        assertEquals(domain.updatedAt, result.updatedAt)
    }

    @Test
    @DisplayName("Deve mapear `Crew` para `CrewEntity` com sucesso")
    fun fromDomainToEntity_ShouldReturnCrewEntity_WithCorrectValues() {
        val result = mapper.fromDomainToEntity(domain)

        assertEquals(entity.adult, result.adult)
        assertEquals(entity.gender, result.gender)
        assertEquals(entity.tmdbId, result.tmdbId)
        assertEquals(entity.knownForDepartment, result.knownForDepartment)
        assertEquals(entity.name, result.name)
        assertEquals(entity.originalName, result.originalName)
        assertEquals(entity.popularity, result.popularity)
        assertEquals(entity.profilePath, result.profilePath)
        assertEquals(entity.creditId, result.creditId)
        assertEquals(entity.department, result.department)
        assertEquals(entity.job, result.job)
        assertEquals(entity.metadata?.createdAt, result.metadata?.createdAt)
        assertEquals(entity.metadata?.updatedAt, result.metadata?.updatedAt)
    }

    @Test
    @DisplayName("Deve mesclar `CrewEntity` com `Crew` com sucesso")
    fun merge_ShouldReturnCrewEntity_WithCorrectValues() {
        val request = Crew(
            id = 1,
            adult = false,
            gender = 0,
            tmdbId = 1,
            knownForDepartment = "Acting",
            name = "author_details_1",
            originalName = "originalName",
            popularity = 5.0,
            profilePath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
            creditId = "creditId",
            department = "department",
            job = "job",
            createdAt = LocalDateTime.parse("2024-06-16T10:00:00"),
            updatedAt = LocalDateTime.parse("2024-06-16T10:00:00")
        )

        val result = mapper.mergeEntityAndRequest(entity, request)

        assertEquals(entity.adult, result.adult)
        assertEquals(entity.gender, result.gender)
        assertEquals(entity.tmdbId, result.tmdbId)
        assertEquals(entity.knownForDepartment, result.knownForDepartment)
        assertEquals(entity.name, result.name)
        assertEquals(entity.originalName, result.originalName)
        assertEquals(entity.popularity, result.popularity)
        assertEquals(entity.profilePath, result.profilePath)
        assertEquals(entity.creditId, result.creditId)
        assertEquals(entity.department, result.department)
        assertEquals(entity.job, result.job)
        assertEquals(entity.metadata?.createdAt, result.metadata?.createdAt)
        assert(result.metadata?.updatedAt != entity.metadata?.updatedAt)
    }

}