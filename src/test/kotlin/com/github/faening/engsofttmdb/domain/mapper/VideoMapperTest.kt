package com.github.faening.engsofttmdb.domain.mapper

import com.github.faening.engsofttmdb.data.model.api.videos.VideoApiData
import com.github.faening.engsofttmdb.data.model.db.GenreEntity
import com.github.faening.engsofttmdb.data.model.db.MetadataEntity
import com.github.faening.engsofttmdb.data.model.db.MovieEntity
import com.github.faening.engsofttmdb.data.model.db.VideoEntity
import com.github.faening.engsofttmdb.data.repository.MovieRepository
import com.github.faening.engsofttmdb.domain.model.Video
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.util.*

@SpringBootTest(properties = ["spring.profiles.active=test"])
class VideoMapperTest {

    private lateinit var apiData: VideoApiData
    private lateinit var entity: VideoEntity
    private lateinit var domain: Video

    private lateinit var movieRepository: MovieRepository

    private lateinit var mapper: VideoMapper

    @BeforeEach
    fun setup() {
        movieRepository = Mockito.mock(MovieRepository::class.java)

        apiData = VideoApiData(
            iso6391 = "en",
            iso31661 = "US",
            name = "video",
            key = "uyzQw8-8k9U",
            site = "YouTube",
            size = 1080,
            type = "Trailer",
            official = true,
            publishedAt = "2024-06-16T10:00:00Z",
            tmdbId = "1"
        )

        entity = VideoEntity(
            id = 1L,
            movie = MovieEntity(
                id = 1L,
                tmdbId = 1022789,
                adult = false,
                backdropPath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
                genres = hashSetOf(
                    GenreEntity(
                        id = 1L,
                        tmdbId = 27,
                        name = "Ação",
                        movies = emptySet(),
                        metadata = MetadataEntity(
                            createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
                            updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
                        )
                    )
                ),
                originalLanguage = "en",
                originalTitle = "movie_1",
                overview = "overview_1",
                popularity = 10.0,
                posterPath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
                releaseDate = LocalDate.parse("2024-06-15"),
                title = "movie_1",
                video = false,
                voteAverage = 5.0,
                voteCount = 1,
                casts = null,
                crews = null,
                reviews = null,
                videos = null,
                metadata = MetadataEntity(
                    createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
                    updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
                )
            ),
            iso6391 = "pt",
            iso31661 = "BR",
            name = "movie_1",
            videoKey = "uyzQw8-8k9U",
            site = "YouTube",
            size = 1080,
            type = "type",
            official = false,
            publishedAt = "2024-06-07T00:37:41.000Z",
            tmdbId = "6662b013eba41395c5528e84",
            metadata = MetadataEntity(
                createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
                updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
            )
        )

        domain = Video(
            id = 1L,
            movieId = 1,
            iso6391 = "pt",
            iso31661 = "BR",
            name = "movie_1",
            videoKey = "uskbXdnbcvg",
            site = "YouTube",
            size = 1080,
            type = "type",
            official = false,
            publishedAt = "2024-06-07T00:37:41.000Z",
            tmdbId = "6662b013eba41395c5528e84",
            createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
            updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
        )

        mapper = VideoMapper(movieRepository)
    }

    @Test
    @DisplayName("Deve mapear `VideoApiData` para `VideoEntity`")
    fun fromApiDataToEntity_ShouldReturnVideoEntity_WithCorrectValues() {
        val result = mapper.fromApiDataToEntity(apiData)

        assertNull(result.id)
        assertNull(result.movie)
        assertEquals(apiData.iso6391, result.iso6391)
        assertEquals(apiData.iso31661, result.iso31661)
        assertEquals(apiData.name, result.name)
        assertEquals(apiData.key, result.videoKey)
        assertEquals(apiData.site, result.site)
        assertEquals(apiData.size, result.size)
        assertEquals(apiData.type, result.type)
        assertEquals(apiData.official, result.official)
        assertEquals(apiData.publishedAt, result.publishedAt)
        assertEquals(apiData.tmdbId, result.tmdbId)
        assertEquals(OffsetDateTime.parse(apiData.publishedAt).toLocalDateTime(), result.metadata?.createdAt)
        assertEquals(OffsetDateTime.parse(apiData.publishedAt).toLocalDateTime(), result.metadata?.updatedAt)
    }

    @Test
    @DisplayName("Deve mapear `VideoEntity` para `Video`")
    fun fromEntityToDomain_ShouldReturnVideo_WithCorrectValues() {
        val result = mapper.fromEntityToDomain(entity)

        assertEquals(entity.id, result.id)
        assertEquals(entity.movie?.id, result.movieId)
        assertEquals(entity.iso6391, result.iso6391)
        assertEquals(entity.iso31661, result.iso31661)
        assertEquals(entity.name, result.name)
        assertEquals(entity.videoKey, result.videoKey)
        assertEquals(entity.site, result.site)
        assertEquals(entity.size, result.size)
        assertEquals(entity.type, result.type)
        assertEquals(entity.official, result.official)
        assertEquals(entity.publishedAt, result.publishedAt)
        assertEquals(entity.tmdbId, result.tmdbId)
        assertEquals(entity.metadata?.createdAt, result.createdAt)
        assertEquals(entity.metadata?.updatedAt, result.updatedAt)
    }

    @Test
    @DisplayName("Deve mapear `Video` para `VideoEntity`")
    fun fromDomainToEntity_ShouldReturnVideoEntity_WithCorrectValues() {
        val expectedMovieEntity = entity.movie
        domain.movieId?.let { id ->
            Mockito.`when`(movieRepository.findById(id))
                .thenReturn(Optional.of(expectedMovieEntity!!))
        }

        val result = mapper.fromDomainToEntity(domain)

        assertEquals(domain.id, result.id)
        assertEquals(expectedMovieEntity, result.movie)
        assertEquals(domain.iso6391, result.iso6391)
        assertEquals(domain.iso31661, result.iso31661)
        assertEquals(domain.name, result.name)
        assertEquals(domain.videoKey, result.videoKey)
        assertEquals(domain.site, result.site)
        assertEquals(domain.size, result.size)
        assertEquals(domain.type, result.type)
        assertEquals(domain.official, result.official)
        assertEquals(domain.publishedAt, result.publishedAt)
        assertEquals(domain.tmdbId, result.tmdbId)
        assertEquals(domain.createdAt, result.metadata?.createdAt)
        assertEquals(domain.updatedAt, result.metadata?.updatedAt)
    }

    @Test
    @DisplayName("Deve mesclar `VideoEntity` e `Video`")
    fun mergeEntityAndRequest_ShouldReturnMergedVideoEntity_WithCorrectValues() {
        val result = mapper.mergeEntityAndRequest(entity, domain)

        assertEquals(entity.id, result.id)
        assertEquals(entity.movie, result.movie)
        assertEquals(domain.iso6391, result.iso6391)
        assertEquals(domain.iso31661, result.iso31661)
        assertEquals(domain.name, result.name)
        assertEquals(domain.videoKey, result.videoKey)
        assertEquals(domain.site, result.site)
        assertEquals(domain.size, result.size)
        assertEquals(domain.type, result.type)
        assertEquals(domain.official, result.official)
        assertEquals(domain.publishedAt, result.publishedAt)
        assertEquals(domain.tmdbId, result.tmdbId)
        assertEquals(entity.metadata?.createdAt, result.metadata?.createdAt)
        assertEquals(domain.updatedAt, result.metadata?.updatedAt)
    }

}