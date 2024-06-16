package com.github.faening.engsofttmdb.domain.mapper

import com.github.faening.engsofttmdb.data.model.api.credits.CastApiData
import com.github.faening.engsofttmdb.data.model.db.CastEntity
import com.github.faening.engsofttmdb.data.model.db.MetadataEntity
import com.github.faening.engsofttmdb.domain.model.Cast
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest(properties = ["spring.profiles.active=test"])
class CastMapperTest {

    private lateinit var apiData: CastApiData
    private lateinit var entity: CastEntity
    private lateinit var domain: Cast

    private lateinit var mapper: CastMapper

    @BeforeEach
    fun setup() {
        apiData = CastApiData(
            adult = false,
            gender = 0,
            id = 1,
            knownForDepartment = "Acting",
            name = "author_details_1",
            originalName = "originalName",
            popularity = 5.0,
            profilePath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
            castId = 1,
            character = "character",
            creditId = "creditId",
            order = 1
        )

        entity = CastEntity(
            id = 1,
            adult = false,
            gender = 0,
            tmdbId = 1,
            knownForDepartment = "Acting",
            name = "author_details_1",
            originalName = "originalName",
            popularity = 5.0,
            profilePath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
            castId = 1,
            character = "character",
            creditId = "creditId",
            order = 1,
            metadata = MetadataEntity(
                createdAt = LocalDateTime.parse("2024-06-16T10:00:00"),
                updatedAt = LocalDateTime.parse("2024-06-16T10:00:00")
            )
        )

        domain = Cast(
            id = 1,
            adult = false,
            gender = 0,
            tmdbId = 1,
            knownForDepartment = "Acting",
            name = "author_details_1",
            originalName = "originalName",
            popularity = 5.0,
            profilePath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
            castId = 1,
            character = "character",
            creditId = "creditId",
            order = 1,
            createdAt = LocalDateTime.parse("2024-06-16T10:00:00"),
            updatedAt = LocalDateTime.parse("2024-06-16T10:00:00")
        )

        mapper = CastMapper()
    }

    @Test
    @DisplayName("Deve mapear `CastApiData` para `CastEntity` com sucesso")
    fun fromApiDataToEntity_ShouldReturnCastEntity_WithCorrectValues() {
        val result = mapper.fromApiDataToEntity(apiData)

        assertEquals(entity.adult, result.adult)
        assertEquals(entity.gender, result.gender)
        assertEquals(entity.tmdbId, result.tmdbId)
        assertEquals(entity.knownForDepartment, result.knownForDepartment)
        assertEquals(entity.name, result.name)
        assertEquals(entity.originalName, result.originalName)
        assertEquals(entity.popularity, result.popularity)
        assertEquals(entity.profilePath, result.profilePath)
        assertEquals(entity.castId, result.castId)
        assertEquals(entity.character, result.character)
        assertEquals(entity.creditId, result.creditId)
        assertEquals(entity.order, result.order)
        assert(result.metadata?.createdAt != entity.metadata?.createdAt)
        assert(result.metadata?.updatedAt != entity.metadata?.updatedAt)

    }

    @Test
    @DisplayName("Deve mapear `CastEntity` para `Cast` com sucesso")
    fun fromEntityToDomain_ShouldReturnCast_WithCorrectValues() {
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
        assertEquals(domain.castId, result.castId)
        assertEquals(domain.character, result.character)
        assertEquals(domain.creditId, result.creditId)
        assertEquals(domain.order, result.order)
        assertEquals(domain.createdAt, result.createdAt)
        assertEquals(domain.updatedAt, result.updatedAt)
    }

    @Test
    @DisplayName("Deve mapear `Cast` para `CastEntity` com sucesso")
    fun fromDomainToEntity_ShouldReturnCastEntity_WithCorrectValues() {
        val result = mapper.fromDomainToEntity(domain)

        assertEquals(entity.id, result.id)
        assertEquals(entity.adult, result.adult)
        assertEquals(entity.gender, result.gender)
        assertEquals(entity.tmdbId, result.tmdbId)
        assertEquals(entity.knownForDepartment, result.knownForDepartment)
        assertEquals(entity.name, result.name)
        assertEquals(entity.originalName, result.originalName)
        assertEquals(entity.popularity, result.popularity)
        assertEquals(entity.profilePath, result.profilePath)
        assertEquals(entity.castId, result.castId)
        assertEquals(entity.character, result.character)
        assertEquals(entity.creditId, result.creditId)
        assertEquals(entity.order, result.order)
        assertEquals(entity.metadata?.createdAt, result.metadata?.createdAt)
        assertEquals(entity.metadata?.updatedAt, result.metadata?.updatedAt)
    }

    @Test
    @DisplayName("Deve mesclar `CastEntity` com `Cast` com sucesso")
    fun mergeEntityAndRequest_ShouldReturnCastEntity_WithCorrectValues() {
        val request = Cast(
            id = 1,
            adult = false,
            gender = 1,
            tmdbId = 1,
            knownForDepartment = "Acting",
            name = "author_details_2",
            originalName = "originalName",
            popularity = 5.0,
            profilePath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
            castId = 1,
            character = "character",
            creditId = "creditId",
            order = 1,
            createdAt = LocalDateTime.parse("2024-06-16T10:00:00"),
            updatedAt = LocalDateTime.parse("2024-06-16T10:00:00")
        )

        val result = mapper.mergeEntityAndRequest(entity, request)

        assertEquals(entity.id, result.id)
        assertEquals(request.adult, result.adult)
        assertEquals(request.gender, result.gender)
        assertEquals(request.tmdbId, result.tmdbId)
        assertEquals(request.knownForDepartment, result.knownForDepartment)
        assertEquals(request.name, result.name)
        assertEquals(request.originalName, result.originalName)
        assertEquals(request.popularity, result.popularity)
        assertEquals(request.profilePath, result.profilePath)
        assertEquals(request.castId, result.castId)
        assertEquals(request.character, result.character)
        assertEquals(request.creditId, result.creditId)
        assertEquals(request.order, result.order)
        assertEquals(entity.metadata?.createdAt, result.metadata?.createdAt)
        assert(result.metadata?.updatedAt != entity.metadata?.updatedAt)
    }
    
}