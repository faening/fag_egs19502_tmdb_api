package com.github.faening.engsofttmdb.domain.mapper

import com.github.faening.engsofttmdb.data.model.api.genres.GenreApiData
import com.github.faening.engsofttmdb.data.model.db.GenreEntity
import com.github.faening.engsofttmdb.data.model.db.MetadataEntity
import com.github.faening.engsofttmdb.domain.model.Genre
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime

@SpringBootTest
@ActiveProfiles("test")
class GenreMapperTest {

    private lateinit var apiData: GenreApiData
    private lateinit var entity: GenreEntity
    private lateinit var domain: Genre

    private lateinit var mapper: GenreMapper

    @BeforeEach
    fun setup() {
        apiData = GenreApiData(
            id = 1,
            name = "genre"
        )

        entity = GenreEntity(
            id = 1,
            tmdbId = 1,
            name = "genre",
            movies = mutableSetOf(),
            metadata = MetadataEntity(
                createdAt = LocalDateTime.parse("2024-06-16T10:00:00"),
                updatedAt = LocalDateTime.parse("2024-06-16T10:00:00")
            )
        )

        domain = Genre(
            id = 1,
            tmdbId = 1,
            name = "genre",
            createdAt = LocalDateTime.parse("2024-06-16T10:00:00"),
            updatedAt = LocalDateTime.parse("2024-06-16T10:00:00")
        )

        mapper = GenreMapper()
    }

    @Test
    @DisplayName("Deve mapear `GenreApiData` para `GenreEntity`")
    fun fromApiDataToEntity_ShouldReturnGenreEntity_WithCorrectValues() {
        val result = mapper.fromApiDataToEntity(apiData)

        assertEquals(entity.name, result.name)
        assertEquals(entity.tmdbId, result.tmdbId)
        assertEquals(entity.movies, result.movies)
        assert(result.metadata?.createdAt != entity.metadata?.createdAt)
        assert(result.metadata?.updatedAt != entity.metadata?.updatedAt)
    }

    @Test
    @DisplayName("Deve mapear `GenreEntity` para `Genre`")
    fun fromEntityToDomain_ShouldReturnGenre_WithCorrectValues() {
        val result = mapper.fromEntityToDomain(entity)

        assertEquals(domain.name, result.name)
        assertEquals(domain.tmdbId, result.tmdbId)
        assertEquals(domain.createdAt, result.createdAt)
        assertEquals(domain.updatedAt, result.updatedAt)
    }

    @Test
    @DisplayName("Deve mapear `Genre` para `GenreEntity`")
    fun fromDomainToEntity_ShouldReturnGenreEntity_WithCorrectValues() {
        val result = mapper.fromDomainToEntity(domain)

        assertEquals(entity.name, result.name)
        assertEquals(entity.tmdbId, result.tmdbId)
        assertEquals(entity.movies, result.movies)
        assertEquals(entity.metadata?.createdAt, result.metadata?.createdAt)
        assertEquals(entity.metadata?.updatedAt, result.metadata?.updatedAt)
    }

    @Test
    @DisplayName("Deve mesclar `GenreEntity` com `Genre`")
    fun mergeEntityAndRequest_ShouldReturnGenreEntity_WithCorrectValues() {
        val request = Genre(
            id = 1,
            tmdbId = 2,
            name = "new genre",
            createdAt = LocalDateTime.parse("2024-06-16T10:00:00"),
            updatedAt = LocalDateTime.parse("2024-06-16T10:00:00")
        )

        val result = mapper.mergeEntityAndRequest(entity, request)

        assertEquals("new genre", result.name)
        assertEquals(request.tmdbId, result.tmdbId)
        assertEquals(request.name, result.name)
        assertEquals(entity.movies, result.movies)
        assertEquals(entity.metadata?.createdAt, result.metadata?.createdAt)
        assert(result.metadata?.updatedAt != entity.metadata?.updatedAt)
    }

}