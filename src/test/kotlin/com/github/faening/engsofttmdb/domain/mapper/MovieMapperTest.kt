package com.github.faening.engsofttmdb.domain.mapper

import com.github.faening.engsofttmdb.data.model.api.movie.MovieApiData
import com.github.faening.engsofttmdb.data.model.db.GenreEntity
import com.github.faening.engsofttmdb.data.model.db.MetadataEntity
import com.github.faening.engsofttmdb.data.model.db.MovieEntity
import com.github.faening.engsofttmdb.data.repository.GenreRepository
import com.github.faening.engsofttmdb.domain.model.Genre
import com.github.faening.engsofttmdb.domain.model.Movie
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@SpringBootTest(properties = ["spring.profiles.active=test"])
class MovieMapperTest {

    private lateinit var apiData: MovieApiData
    private lateinit var entity: MovieEntity
    private lateinit var domain: Movie

    private lateinit var genreRepository: GenreRepository
    private lateinit var genreMapper: GenreMapper

    private lateinit var mapper: MovieMapper

    @BeforeEach
    fun setup() {
        genreRepository = Mockito.mock(GenreRepository::class.java)
        genreMapper = Mockito.mock(GenreMapper::class.java)

        apiData = MovieApiData(
            adult = false,
            backdropPath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
            genreIds = listOf(1L),
            id = 1L,
            originalLanguage = "en",
            originalTitle = "movie",
            overview = "overview",
            popularity = 10.0,
            posterPath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
            releaseDate = "2024-06-16",
            title = "movie",
            video = false,
            voteAverage = 10.0,
            voteCount = 100
        )

        entity = MovieEntity(
            id = 1L,
            tmdbId = 1022789,
            adult = false,
            backdropPath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
            genres = hashSetOf(
                GenreEntity(
                    id = 1L,
                    tmdbId = 27,
                    name = "Horror",
                    movies = mutableSetOf(),
                    metadata = MetadataEntity(
                        createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
                        updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
                    )
                )
            ),
            originalLanguage = "en",
            originalTitle = "movie",
            overview = "overview",
            popularity = 10.0,
            posterPath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
            releaseDate = LocalDate.parse("2024-06-16"),
            title = "movie",
            video = false,
            voteAverage = 10.0,
            voteCount = 100,
            metadata = MetadataEntity(
                createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
                updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
            )
        )

        domain = Movie(
            id = 1L,
            adult = false,
            backdropPath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
            genreIds = listOf(1L),
            tmdbId = 1022789,
            originalLanguage = "en",
            originalTitle = "movie",
            overview = "overview",
            popularity = 10.0,
            posterPath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
            releaseDate = LocalDate.parse("2024-06-16"),
            title = "movie",
            video = false,
            voteAverage = 10.0,
            voteCount = 100,
            createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
            updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
        )

        mapper = MovieMapper(genreRepository, genreMapper)
    }

    @Test
    @DisplayName("Deve mapear `MovieApiData` para `MovieEntity`")
    fun fromApiDataToEntity_ShouldReturnMovieEntity_WithCorrectValues() {
        val expectedGenreEntity = entity.genres.first()
        apiData.genreIds.first().let { id ->
            Mockito.`when`(genreRepository.findByTmdbId(id))
                .thenReturn(expectedGenreEntity)
        }

        val result = mapper.fromApiDataToEntity(apiData)

        assertNull(result.id)
        assertEquals(apiData.id, result.tmdbId)
        assertEquals(apiData.adult, result.adult)
        assertEquals(apiData.backdropPath, result.backdropPath)
        assertTrue(result.genres.contains(expectedGenreEntity))
        assertEquals(apiData.originalLanguage, result.originalLanguage)
        assertEquals(apiData.originalTitle, result.originalTitle)
        assertEquals(apiData.overview, result.overview)
        assertEquals(apiData.popularity, result.popularity)
        assertEquals(apiData.posterPath, result.posterPath)
        assertEquals(LocalDate.parse(apiData.releaseDate), result.releaseDate)
        assertEquals(apiData.title, result.title)
        assertEquals(apiData.video, result.video)
        assertEquals(apiData.voteAverage, result.voteAverage)
        assertEquals(apiData.voteCount, result.voteCount)
    }

    @Test
    @DisplayName("Deve mapear `MovieEntity` para `Movie`")
    fun fromEntityToDomain_ShouldReturnMovie_WithCorrectValues() {
        val expectedGenreDomain = Genre(id = 1L, name = "Horror")
        entity.genres.first().let { genreEntity ->
            Mockito.`when`(genreMapper.fromEntityToDomain(genreEntity))
                .thenReturn(expectedGenreDomain)
        }

        val result = mapper.fromEntityToDomain(entity)

        assertEquals(entity.id, result.id)
        assertEquals(entity.adult, result.adult)
        assertEquals(entity.backdropPath, result.backdropPath)
        assertEquals(listOf(expectedGenreDomain.id), result.genreIds)
        assertEquals(entity.tmdbId, result.tmdbId)
        assertEquals(entity.originalLanguage, result.originalLanguage)
        assertEquals(entity.originalTitle, result.originalTitle)
        assertEquals(entity.overview, result.overview)
        assertEquals(entity.popularity, result.popularity)
        assertEquals(entity.posterPath, result.posterPath)
        assertEquals(entity.releaseDate, result.releaseDate)
        assertEquals(entity.title, result.title)
        assertEquals(entity.video, result.video)
        assertEquals(entity.voteAverage, result.voteAverage)
        assertEquals(entity.voteCount, result.voteCount)
        assertEquals(entity.metadata?.createdAt, result.createdAt)
        assertEquals(entity.metadata?.updatedAt, result.updatedAt)
    }

    @Test
    @DisplayName("Deve mapear `Movie` para `MovieEntity`")
    fun fromDomainToEntity_ShouldReturnMovieEntity_WithCorrectValues() {
        val expectedGenreEntity = entity.genres.first()
        domain.genreIds.first().let { id ->
            Mockito.`when`(genreRepository.findById(id))
                .thenReturn(Optional.of(expectedGenreEntity))
        }

        val result = mapper.fromDomainToEntity(domain)

        assertEquals(domain.id ?: 0, result.id)
        assertEquals(domain.adult ?: false, result.adult)
        assertEquals(domain.backdropPath, result.backdropPath)
        assertTrue(result.genres.contains(expectedGenreEntity))
        assertEquals(domain.tmdbId, result.tmdbId)
        assertEquals(domain.originalLanguage ?: "", result.originalLanguage)
        assertEquals(domain.originalTitle ?: "", result.originalTitle)
        assertEquals(domain.overview ?: "", result.overview)
        assertEquals(domain.popularity ?: 0.0, result.popularity)
        assertEquals(domain.posterPath, result.posterPath)
        assertEquals(domain.releaseDate, result.releaseDate)
        assertEquals(domain.title, result.title)
        assertEquals(domain.video ?: false, result.video)
        assertEquals(domain.voteAverage ?: 0.0, result.voteAverage)
        assertEquals(domain.voteCount ?: 0, result.voteCount)
        assertNull(result.metadata)
    }

    @Suppress("USELESS_ELVIS")
    @Test
    @DisplayName("Deve mesclar `MovieEntity` e `Movie`")
    fun mergeEntityAndRequest_ShouldReturnMergedMovieEntity_WithCorrectValues() {
        val expectedGenreEntity = entity.genres.first()
        domain.genreIds.first().let { id ->
            Mockito.`when`(genreRepository.findById(id))
                .thenReturn(Optional.of(expectedGenreEntity))
        }

        val result = mapper.mergeEntityAndRequest(entity, domain)

        assertEquals(entity.id, result.id)
        assertEquals(domain.adult ?: entity.adult, result.adult)
        assertEquals(domain.backdropPath ?: entity.backdropPath, result.backdropPath)
        assertTrue(result.genres.contains(expectedGenreEntity))
        assertEquals(domain.tmdbId ?: entity.tmdbId, result.tmdbId)
        assertEquals(domain.originalLanguage ?: entity.originalLanguage, result.originalLanguage)
        assertEquals(domain.originalTitle ?: entity.originalTitle, result.originalTitle)
        assertEquals(domain.overview ?: entity.overview, result.overview)
        assertEquals(domain.popularity ?: entity.popularity, result.popularity)
        assertEquals(domain.posterPath ?: entity.posterPath, result.posterPath)
        assertEquals(domain.releaseDate ?: entity.releaseDate, result.releaseDate)
        assertEquals(domain.title ?: entity.title, result.title)
        assertEquals(domain.video ?: entity.video, result.video)
        assertEquals(domain.voteAverage ?: entity.voteAverage, result.voteAverage)
        assertEquals(domain.voteCount ?: entity.voteCount, result.voteCount)
        assertEquals(entity.metadata, result.metadata)
    }

}