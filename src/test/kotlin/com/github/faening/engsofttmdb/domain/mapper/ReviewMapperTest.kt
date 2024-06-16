package com.github.faening.engsofttmdb.domain.mapper

import com.github.faening.engsofttmdb.data.model.api.reviews.AuthorDetailsApiData
import com.github.faening.engsofttmdb.data.model.api.reviews.ReviewApiData
import com.github.faening.engsofttmdb.data.model.db.AuthorDetailsEntity
import com.github.faening.engsofttmdb.data.model.db.MetadataEntity
import com.github.faening.engsofttmdb.data.model.db.MovieEntity
import com.github.faening.engsofttmdb.data.model.db.ReviewEntity
import com.github.faening.engsofttmdb.data.repository.AuthorDetailsRepository
import com.github.faening.engsofttmdb.data.repository.MovieRepository
import com.github.faening.engsofttmdb.domain.model.AuthorDetails
import com.github.faening.engsofttmdb.domain.model.Review
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@SpringBootTest
@ActiveProfiles("test")
class ReviewMapperTest {

    private lateinit var apiData: ReviewApiData
    private lateinit var entity: ReviewEntity
    private lateinit var domain: Review

    private lateinit var authorDetailsRepository: AuthorDetailsRepository
    private lateinit var authorDetailsMapper: AuthorDetailsMapper
    private lateinit var movieRepository: MovieRepository

    private lateinit var mapper: ReviewMapper

    @BeforeEach
    fun setup() {
        authorDetailsRepository = Mockito.mock(AuthorDetailsRepository::class.java)
        authorDetailsMapper = Mockito.mock(AuthorDetailsMapper::class.java)
        movieRepository = Mockito.mock(MovieRepository::class.java)

        apiData = ReviewApiData(
            author = "author",
            authorDetails = AuthorDetailsApiData(
                name = "name",
                username = "username",
                avatarPath = "avatarPath",
                rating = 10
            ),
            content = "content",
            tmdbId = "6669f31a282af652f2d05392",
            url = "https://www.themoviedb.org/review/6669f31a282af652f2d05392",
            createdAt = "2024-06-16T10:00:00Z",
            updatedAt = "2024-06-16T10:00:00Z"
        )

        entity = ReviewEntity(
            id = 1,
            movie = MovieEntity(
                id = 1,
                tmdbId = 1,
                adult = false,
                backdropPath = "backdropPath",
                genres = mutableSetOf(),
                originalLanguage = "en",
                originalTitle = "originalTitle",
                overview = "overview",
                popularity = 10.0,
                posterPath = "posterPath",
                releaseDate = LocalDate.parse("2024-06-16"),
                title = "title",
                video = false,
                voteAverage = 10.0,
                voteCount = 10,
                metadata = MetadataEntity(
                    createdAt = LocalDateTime.parse("2024-06-16T10:00:00"),
                    updatedAt = LocalDateTime.parse("2024-06-16T10:00:00")
                )
            ),
            author = "author",
            authorDetails = AuthorDetailsEntity(
                id = 1,
                name = "name",
                username = "username",
                avatarPath = "avatarPath",
                rating = 10,
                reviews = mutableListOf(),
                metadata = MetadataEntity(
                    createdAt = LocalDateTime.parse("2024-06-16T10:00:00"),
                    updatedAt = LocalDateTime.parse("2024-06-16T10:00:00")
                )
            ),
            content = "content",
            tmdbId = "6669f31a282af652f2d05392",
            url = "https://www.themoviedb.org/review/6669f31a282af652f2d05392",
            metadata = MetadataEntity(
                createdAt = LocalDateTime.parse("2024-06-16T10:00:00"),
                updatedAt = LocalDateTime.parse("2024-06-16T10:00:00")
            )
        )

        domain = Review(
            id = 1,
            movieId = 1,
            author = "author",
            authorDetails = AuthorDetails(
                id = 1,
                name = "name",
                username = "username",
                avatarPath = "avatarPath",
                rating = 10,
                createdAt = LocalDateTime.parse("2024-06-16T10:00:00"),
                updatedAt = LocalDateTime.parse("2024-06-16T10:00:00")

            ),
            content = "content",
            tmdbId = "6669f31a282af652f2d05392",
            url = "https://www.themoviedb.org/review/6669f31a282af652f2d05392",
            createdAt = LocalDateTime.parse("2024-06-16T10:00:00"),
            updatedAt = LocalDateTime.parse("2024-06-16T10:00:00")
        )

        mapper = ReviewMapper(authorDetailsRepository, authorDetailsMapper, movieRepository)
    }

    @Test
    @DisplayName("Deve mapear `ReviewApiData` para `ReviewEntity`")
    fun fromApiDataToEntity_ShouldReturnReviewEntity_WithCorrectValues() {
        val expectedAuthorDetailsEntity = entity.authorDetails
        Mockito.`when`(authorDetailsRepository
            .findByNameOrUsernameIgnoreCase(apiData.author, apiData.author))
            .thenReturn(expectedAuthorDetailsEntity)

        val result = mapper.fromApiDataToEntity(apiData)

        assertEquals(entity.author, result.author)
        assertEquals(entity.content, result.content)
        assertEquals(entity.tmdbId, result.tmdbId)
        assertEquals(entity.url, result.url)
        assertEquals(expectedAuthorDetailsEntity, result.authorDetails)
        assertEquals(entity.metadata?.createdAt, result.metadata?.createdAt)
        assertEquals(entity.metadata?.updatedAt, result.metadata?.updatedAt)
    }

    @Test
    @DisplayName("Deve mapear `ReviewEntity` para `Review`")
    fun fromEntityToDomain_ShouldReturnReview_WithCorrectValues() {
        val expectedAuthorDetails = domain.authorDetails

        entity.authorDetails?.id?.let { id ->
            Mockito.`when`(authorDetailsRepository.findById(id))
                .thenReturn(Optional.of(entity.authorDetails!!))

            Mockito.`when`(authorDetailsMapper.fromEntityToDomain(entity.authorDetails!!))
                .thenReturn(expectedAuthorDetails)
        }

        val result = mapper.fromEntityToDomain(entity)

        assertEquals(entity.id, result.id)
        assertEquals(entity.movie?.id, result.movieId)
        assertEquals(entity.author, result.author)
        assertEquals(expectedAuthorDetails, result.authorDetails)
        assertEquals(entity.content, result.content)
        assertEquals(entity.tmdbId, result.tmdbId)
        assertEquals(entity.url, result.url)
        assertEquals(entity.metadata?.createdAt, result.createdAt)
        assertEquals(entity.metadata?.updatedAt, result.updatedAt)
    }

    @Test
    @DisplayName("Deve mapear `Review` para `ReviewEntity`")
    fun fromDomainToEntity_ShouldReturnReviewEntity_WithCorrectValues() {
        val expectedAuthorDetailsEntity = entity.authorDetails
        val expectedMovieEntity = entity.movie
        domain.authorDetails?.id?.let { id ->
            Mockito.`when`(authorDetailsRepository.findById(id))
                .thenReturn(Optional.of(expectedAuthorDetailsEntity!!))
        }
        domain.movieId?.let { id ->
            Mockito.`when`(movieRepository.findById(id))
                .thenReturn(Optional.of(expectedMovieEntity!!))
        }

        val result = mapper.fromDomainToEntity(domain)

        assertEquals(domain.id, result.id)
        assertEquals(expectedMovieEntity?.id, result.movie?.id)
        assertEquals(domain.author, result.author)
        assertEquals(expectedAuthorDetailsEntity, result.authorDetails)
        assertEquals(domain.content, result.content)
        assertEquals(domain.tmdbId, result.tmdbId)
        assertEquals(domain.url, result.url)
        assertEquals(domain.createdAt, result.metadata?.createdAt)
        assertEquals(domain.updatedAt, result.metadata?.updatedAt)
    }

    @Test
    @DisplayName("Deve mesclar `ReviewEntity` e `Review` corretamente")
    fun mergeEntityAndRequest_ShouldReturnMergedReviewEntity_WithCorrectValues() {
        val request = Review(
            id = 1,
            movieId = 1,
            author = "new author",
            authorDetails = AuthorDetails(
                id = 1,
                name = "new name",
                username = "new username",
                avatarPath = "new avatarPath",
                rating = 10,
                createdAt = LocalDateTime.parse("2024-06-16T10:00:00"),
                updatedAt = LocalDateTime.parse("2024-06-16T10:00:00")
            ),
            content = "new content",
            tmdbId = "new tmdbId",
            url = "new url",
            createdAt = LocalDateTime.parse("2024-06-16T10:00:00"),
            updatedAt = LocalDateTime.parse("2024-06-16T10:00:00")
        )

        val expectedAuthorDetailsEntity = entity.authorDetails
        request.authorDetails?.id?.let { id ->
            Mockito.`when`(authorDetailsRepository.findById(id))
                .thenReturn(Optional.of(expectedAuthorDetailsEntity!!))
        }

        val result = mapper.mergeEntityAndRequest(entity, request)

        assertEquals(entity.id, result.id)
        assertEquals(entity.movie, result.movie)
        assertEquals(request.author, result.author)
        assertEquals(expectedAuthorDetailsEntity, result.authorDetails)
        assertEquals(request.content, result.content)
        assertEquals(request.tmdbId, result.tmdbId)
        assertEquals(request.url, result.url)
        assertEquals(entity.metadata?.createdAt, result.metadata?.createdAt)
        assertNotNull(result.metadata?.updatedAt)
    }

}