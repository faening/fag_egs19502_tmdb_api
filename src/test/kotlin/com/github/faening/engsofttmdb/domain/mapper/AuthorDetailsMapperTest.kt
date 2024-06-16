package com.github.faening.engsofttmdb.domain.mapper

import com.github.faening.engsofttmdb.data.model.api.reviews.AuthorDetailsApiData
import com.github.faening.engsofttmdb.data.model.db.AuthorDetailsEntity
import com.github.faening.engsofttmdb.data.model.db.MetadataEntity
import com.github.faening.engsofttmdb.domain.model.AuthorDetails
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest(properties = ["spring.profiles.active=test"])
class AuthorDetailsMapperTest {

    private lateinit var apiData: AuthorDetailsApiData
    private lateinit var entity: AuthorDetailsEntity
    private lateinit var domain: AuthorDetails

    private lateinit var mapper: AuthorDetailsMapper

    @BeforeEach
    fun setup() {
        apiData = AuthorDetailsApiData(
            name = "author_details_1",
            username = "author_details_1",
            avatarPath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
            rating = 5
        )

        entity = AuthorDetailsEntity(
            id = 1,
            name = "author_details_1",
            username = "author_details_1",
            avatarPath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
            rating = 5,
            metadata = MetadataEntity(
                createdAt = LocalDateTime.parse("2024-06-16T10:00:00"),
                updatedAt = LocalDateTime.parse("2024-06-16T10:00:00")
            )
        )

        domain = AuthorDetails(
            id = 1,
            name = "author_details_1",
            username = "author_details_1",
            avatarPath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
            rating = 5,
            createdAt = LocalDateTime.parse("2024-06-16T10:00:00"),
            updatedAt = LocalDateTime.parse("2024-06-16T10:00:00")
        )

        mapper = AuthorDetailsMapper()
    }

    @Test
    @DisplayName("Deve mapear `AuthorDetailsApiData` para `AuthorDetailsEntity` com sucesso")
    fun fromApiDataToEntity_ShouldReturnAuthorDetailsEntity_WithCorrectValues() {
        val result = mapper.fromApiDataToEntity(apiData)

        assertEquals(entity.name, result.name)
        assertEquals(entity.username, result.username)
        assertEquals(entity.avatarPath, result.avatarPath)
        assertEquals(entity.rating, result.rating)
        assert(result.metadata?.createdAt != entity.metadata?.createdAt)
        assert(result.metadata?.updatedAt != entity.metadata?.updatedAt)
    }

    @Test
    @DisplayName("Deve mapear `AuthorDetailsEntity` para `AuthorDetails` com sucesso")
    fun fromEntityToDomain_ShouldReturnAuthorDetails_WithCorrectValues() {
        val result = mapper.fromEntityToDomain(entity)

        assertEquals(domain.id, result.id)
        assertEquals(domain.name, result.name)
        assertEquals(domain.username, result.username)
        assertEquals(domain.avatarPath, result.avatarPath)
        assertEquals(domain.rating, result.rating)
        assertEquals(domain.createdAt, result.createdAt)
        assertEquals(domain.updatedAt, result.updatedAt)
    }

    @Test
    @DisplayName("Deve mapear `AuthorDetails` para `AuthorDetailsEntity` com sucesso")
    fun fromDomainToEntity_ShouldReturnAuthorDetailsEntity_WithCorrectValues() {
        val result = mapper.fromDomainToEntity(domain)

        assertEquals(entity.id, result.id)
        assertEquals(entity.name, result.name)
        assertEquals(entity.username, result.username)
        assertEquals(entity.avatarPath, result.avatarPath)
        assertEquals(entity.rating, result.rating)
        assertEquals(entity.metadata?.createdAt, result.metadata?.createdAt)
        assertEquals(entity.metadata?.updatedAt, result.metadata?.updatedAt)
    }

    @Test
    @DisplayName("Deve mesclar `AuthorDetailsEntity` com `AuthorDetails` com sucesso")
    fun mergeEntityAndRequest_ShouldReturnAuthorDetailsEntity_WithCorrectValues() {
        val request = AuthorDetails(
            id = 1,
            name = "author_details_2",
            username = "author_details_2",
            avatarPath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
            rating = 5,
            createdAt = LocalDateTime.parse("2024-06-16T10:00:00"),
            updatedAt = LocalDateTime.parse("2024-06-16T10:00:00")
        )

        val result = mapper.mergeEntityAndRequest(entity, request)

        assertEquals(entity.id, result.id)
        assertEquals(request.name, result.name)
        assertEquals(request.username, result.username)
        assertEquals(request.avatarPath, result.avatarPath)
        assertEquals(request.rating, result.rating)
        assertEquals(entity.metadata?.createdAt, result.metadata?.createdAt)
        assert(result.metadata?.updatedAt != entity.metadata?.updatedAt)
    }

}