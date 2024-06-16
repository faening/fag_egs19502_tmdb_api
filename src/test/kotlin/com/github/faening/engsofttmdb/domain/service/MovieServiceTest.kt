package com.github.faening.engsofttmdb.domain.service

import com.github.faening.engsofttmdb.data.model.db.*
import com.github.faening.engsofttmdb.data.repository.MovieRepository
import com.github.faening.engsofttmdb.domain.mapper.*
import com.github.faening.engsofttmdb.domain.model.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@SpringBootTest
@ActiveProfiles("test")
class MovieServiceTest {

    private lateinit var repository: MovieRepository
    private lateinit var movieMapper: MovieMapper
    private lateinit var castMapper: CastMapper
    private lateinit var crewMapper: CrewMapper
    private lateinit var reviewMapper: ReviewMapper
    private lateinit var videoMapper: VideoMapper
    private lateinit var service: MovieService

    private lateinit var serviceSpy: MovieService

    private var expectedEntity: MutableList<MovieEntity> = mutableListOf()
    private var expectedValueObject: MutableList<Movie> = mutableListOf()

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(MovieRepository::class.java)
        movieMapper = Mockito.mock(MovieMapper::class.java)
        castMapper = Mockito.mock(CastMapper::class.java)
        crewMapper = Mockito.mock(CrewMapper::class.java)
        reviewMapper = Mockito.mock(ReviewMapper::class.java)
        videoMapper = Mockito.mock(VideoMapper::class.java)
        service = MovieService(repository, movieMapper, castMapper, crewMapper, reviewMapper, videoMapper)

        serviceSpy = Mockito.spy(service)

        expectedEntity = mutableListOf(
            MovieEntity(
                id = 1L,
                tmdbId = 653346,
                adult = false,
                backdropPath = "/fqv8v6AycXKsivp1T5yKtLbGXce.jpg",
                genres = hashSetOf(
                    GenreEntity(
                        id = 1L,
                        tmdbId = 28,
                        name = "Action",
                        movies = mutableSetOf(),
                        metadata = MetadataEntity(
                            createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
                            updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
                        )
                    )
                ),
                originalLanguage = "en",
                originalTitle = "original_title",
                overview = "overview",
                popularity = 1.0,
                posterPath = "/dzDK2TMXsxrolGVdZwNGcOlZqrF.jpg",
                releaseDate = LocalDate.parse("2021-03-12"),
                title = "movie_1",
                video = false,
                voteAverage = 1.0,
                voteCount = 1,
                casts = hashSetOf(
                    CastEntity(
                        id = 1L,
                        adult = false,
                        gender = 1,
                        tmdbId = 56322,
                        knownForDepartment = "Acting",
                        name = "cast_1",
                        originalName = "cast_1",
                        popularity = 26.804,
                        profilePath = "/rwmvRonpluV6dCPiQissYrchvSD.jpg",
                        castId = 4,
                        character = "Joy (voice)",
                        creditId = "631bd7450bb076007b78d023",
                        order = 0,
                        metadata = MetadataEntity(
                            createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
                            updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
                        )
                    )
                ),
                crews = hashSetOf(
                    CrewEntity(
                        id = 1L,
                        adult = false,
                        gender = 1,
                        tmdbId = 182001,
                        knownForDepartment = "Writing",
                        name = "crew_1",
                        originalName = "crew_1",
                        popularity = 26.804,
                        profilePath = "/rwmvRonpluV6dCPiQissYrchvSD.jpg",
                        creditId = "631bd6ad0d2f53007f5c9c41",
                        department = "Directing",
                        job = "Director",
                        metadata = MetadataEntity(
                            createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
                            updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
                        )
                    )
                ),
                reviews = mutableListOf(
                    ReviewEntity(
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
                        author = "author_1",
                        authorDetails = AuthorDetailsEntity(
                            id = 1L,
                            name = "author_1",
                            username = "author_1",
                            avatarPath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
                            rating = 5,
                            metadata = MetadataEntity(
                                createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
                                updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
                            )
                        ),
                        content = "is a heartwarming and imaginative sequel that successfully",
                        tmdbId = "666193da58a1f88965b77e41",
                        url = "https://www.themoviedb.org/review/666193da58a1f88965b77e41",
                        metadata = MetadataEntity(
                            createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
                            updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
                        )
                    )
                ),
                videos = mutableListOf(
                    VideoEntity(
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
                ),
                metadata = MetadataEntity(
                    createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
                    updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
                )
            )
        )

        expectedValueObject = mutableListOf(
            Movie(
                id = 1L,
                tmdbId = 653346,
                adult = false,
                backdropPath = "/fqv8v6AycXKsivp1T5yKtLbGXce.jpg",
                genreIds = listOf(1),
                originalLanguage = "en",
                originalTitle = "original_title",
                overview = "overview",
                popularity = 1.0,
                posterPath = "/dzDK2TMXsxrolGVdZwNGcOlZqrF.jpg",
                releaseDate = LocalDate.parse("2021-03-12"),
                title = "movie_1",
                video = false,
                voteAverage = 1.0,
                voteCount = 1,
                createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
                updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
            ),
        )

        Mockito.reset(repository, movieMapper, castMapper, crewMapper, reviewMapper, videoMapper)
    }

    @Test
    @DisplayName("Deve retornar uma lista de entidades do tipo `MovieEntity`")
    fun getAllEntities_ShouldReturnListOfMovieEntities_WhenRequestIsSuccessful() {
        val expected = expectedEntity
        `when`(repository.findAll()).thenReturn(expected)

        val actual = serviceSpy.getAllEntities()

        assertEquals(expected, actual, "Deve retornar uma lista de entidades do tipo `MovieEntity`")
    }

    @Test
    @DisplayName("Deve retornar uma lista de `Movie`")
    fun getAll_ShouldReturnListOfMovie_WhenRequestIsSuccessful() {
        val expectedEntities = expectedEntity
        val expected = expectedEntities.map { movieMapper.fromEntityToDomain(it) }
        `when`(repository.findAll()).thenReturn(expectedEntities)

        val actual = serviceSpy.getAll()

        assertEquals(expected, actual, "Deve retornar uma lista de `Movie`")
    }

    @Test
    @DisplayName("Deve retornar uma entidade `MovieEntity` pelo ID")
    fun getEntityById_ShouldReturnMovieEntity_WhenIdIsGiven() {
        val expected = expectedEntity.first()
        val id = expected.id
        `when`(id?.let { repository.findById(it) }).thenReturn(Optional.of(expected))

        val actual = id?.let { serviceSpy.getEntityById(it) }

        assertEquals(expected, actual, "Deve retornar uma entidade `MovieEntity` pelo ID")
    }

    @Test
    @DisplayName("Deve retornar um `Movie` pelo ID")
    fun getById_ShouldReturnMovie_WhenIdIsGiven() {
        val expectedEntity = expectedEntity.first()
        val id = expectedEntity.id
        val expected = movieMapper.fromEntityToDomain(expectedEntity)
        `when`(id?.let { repository.findById(it) }).thenReturn(Optional.of(expectedEntity))

        val actual = id?.let { serviceSpy.getById(it) }

        assertEquals(expected, actual, "Deve retornar um `Movie` pelo ID")
    }

    @Test
    @DisplayName("Deve salvar uma entidade `MovieEntity`")
    fun saveEntity_ShouldSaveMovieEntity_WhenEntityIsGiven() {
        val expected = expectedEntity.first()
        `when`(repository.save(expected)).thenReturn(expected)

        val actual = serviceSpy.saveEntity(expected)

        assertEquals(expected, actual, "Deve salvar uma entidade `MovieEntity`")
    }

    @Test
    @DisplayName("Deve salvar uma lista de `MovieEntity`")
    fun saveAllEntities_ShouldSaveListOfMovieEntities_WhenListOfEntitiesIsGiven() {
        val expected = expectedEntity
        `when`(repository.saveAll(expected)).thenReturn(expected)

        val actual = serviceSpy.saveAllEntities(expected)

        assertEquals(expected, actual, "Deve salvar uma lista de `MovieEntity`")
    }

    @Test
    @DisplayName("Deve salvar um `Movie`")
    fun save_ShouldSaveMovie_WhenRequestIsGiven() {
        val request = expectedValueObject.first()
        val entity = movieMapper.fromDomainToEntity(request)

        @Suppress("SENSELESS_COMPARISON")
        if (entity != null) {
            val expected = movieMapper.fromEntityToDomain(entity)

            `when`(movieMapper.fromDomainToEntity(request)).thenReturn(entity)
            `when`(repository.save(entity)).thenReturn(entity)

            val actual = serviceSpy.save(request)

            assertEquals(expected, actual, "Deve salvar um `Movie`")
        }
    }

    @Test
    @DisplayName("Deve salvar uma lista de `Movie`")
    fun saveAll_ShouldSaveListOfMovie_WhenListOfRequestsIsGiven() {
        val request = expectedValueObject
        val entities = request.map { movieMapper.fromDomainToEntity(it) }
        val expected = entities.map { movieMapper.fromEntityToDomain(it) }

        for (i in request.indices) {
            `when`(movieMapper.fromDomainToEntity(request[i])).thenReturn(entities[i])
        }

        `when`(repository.saveAll(entities)).thenReturn(entities)

        for (i in entities.indices) {
            `when`(movieMapper.fromEntityToDomain(entities[i])).thenReturn(expected[i])
        }

        val actual = serviceSpy.saveAll(request)

        assertEquals(expected, actual, "Deve salvar uma lista de `Movie`")
    }

    @Test
    @DisplayName("Deve atualizar uma entidade `MovieEntity`")
    fun updateEntity_ShouldUpdateMovieEntity_WhenEntityIsGiven() {
        val entity = expectedEntity.first()
        val expected = movieMapper.fromEntityToDomain(entity)
        `when`(repository.save(entity)).thenReturn(entity)
        `when`(movieMapper.fromEntityToDomain(entity)).thenReturn(expected)

        val actual = serviceSpy.updateEntity(entity)

        assertEquals(expected, actual, "Deve atualizar uma entidade `MovieEntity`")
    }

    @Test
    @DisplayName("Deve atualizar um `Movie`")
    fun update_ShouldUpdateMovie_WhenIdAndRequestAreGiven() {
        val id = 1L
        val request = expectedValueObject.first()
        val entity = movieMapper.fromDomainToEntity(request)

        @Suppress("SENSELESS_COMPARISON")
        if (entity != null) {
            val mergedEntity = entity.copy(originalTitle = "author_updated")
            val expected = movieMapper.fromEntityToDomain(mergedEntity)

            `when`(serviceSpy.getEntityById(id)).thenReturn(entity)
            `when`(movieMapper.mergeEntityAndRequest(entity, request)).thenReturn(mergedEntity)
            `when`(serviceSpy.updateEntity(mergedEntity)).thenReturn(expected)

            val actual = serviceSpy.update(id, request)

            assertEquals(expected, actual, "Deve atualizar um `Movie`")
        }
    }

    @Test
    @DisplayName("Deve deletar uma entidade `MovieEntity`")
    fun deleteEntity_ShouldDeleteMovieEntity_WhenEntityIsGiven() {
        val entity = expectedEntity.first()
        val actual = serviceSpy.deleteEntity(entity)

        verify(repository).delete(entity)
        assertTrue(actual, "Deve deletar uma entidade `MovieEntitys`")
    }

    @Test
    @DisplayName("Deve deletar um `Movie` pelo ID")
    fun delete_ShouldDeleteMovie_WhenIdIsGiven() {
        val id = 1L
        val entity = expectedEntity.first()

        `when`(repository.findById(id)).thenReturn(Optional.of(entity))
        `when`(serviceSpy.deleteEntity(entity)).thenReturn(true)

        val actual = serviceSpy.delete(id)

        verify(serviceSpy).getEntityById(id)
        verify(serviceSpy).deleteEntity(entity)
        assertTrue(actual, "Deve deletar um `Movie` pelo ID")
    }

    @Test
    @DisplayName("Deve retornar uma lista de `Cast`")
    fun getMovieCasts_ShouldReturnListOfCast_WhenMovieIdIsGiven() {
        val movieId = 1L
        val expectedCast = Cast(
            id = 1L,
            adult = false,
            gender = 1,
            tmdbId = 56322,
            knownForDepartment = "Acting",
            name = "cast_1",
            originalName = "cast_1",
            popularity = 26.804,
            profilePath = "/rwmvRonpluV6dCPiQissYrchvSD.jpg",
            castId = 4,
            character = "Joy (voice)",
            creditId = "631bd7450bb076007b78d023",
            order = 0,
            createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
            updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
        )
        val expectedCasts = listOf(expectedCast)

        `when`(repository.findById(movieId)).thenReturn(Optional.of(expectedEntity[0]))
        `when`(castMapper.fromEntityToDomain(expectedEntity[0].casts!!.first())).thenReturn(expectedCast)

        val result = service.getMovieCasts(movieId)

        assertEquals(expectedCasts, result)
    }

    @Test
    @DisplayName("Deve retornar uma lista de `Crew`")
    fun getMovieCrew_ShouldReturnListOfCrew_WhenMovieIdIsGiven() {
        val movieId = 1L
        val expectedCrew = Crew(
            id = 1L,
            adult = false,
            gender = 1,
            tmdbId = 182001,
            knownForDepartment = "Writing",
            name = "crew_1",
            originalName = "crew_1",
            popularity = 26.804,
            profilePath = "/rwmvRonpluV6dCPiQissYrchvSD.jpg",
            creditId = "631bd6ad0d2f53007f5c9c41",
            department = "Directing",
            job = "Director",
            createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
            updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
        )
        val expectedCasts = listOf(expectedCrew)

        `when`(repository.findById(movieId)).thenReturn(Optional.of(expectedEntity[0]))
        `when`(crewMapper.fromEntityToDomain(expectedEntity[0].crews!!.first())).thenReturn(expectedCrew)

        val result = service.getMovieCrews(movieId)

        assertEquals(expectedCasts, result)
    }

    @Test
    @DisplayName("Deve retornar uma lista de `Review`")
    fun getMovieReview_ShouldReturnListOfReview_WhenMovieIdIsGiven() {
        val movieId = 1L
        val expectedReview = Review(
            id = 1L,
            movieId = 1,
            author = "author_1",
            authorDetails = AuthorDetails(
                id = 1L,
                name = "author_1",
                username = "author_1",
                avatarPath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
                rating = 5,
                createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
                updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
            ),
            content = "is a heartwarming and imaginative sequel that successfully",
            tmdbId = "666193da58a1f88965b77e41",
            url = "https://www.themoviedb.org/review/666193da58a1f88965b77e41",
            createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
            updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
        )
        val expectedCasts = listOf(expectedReview)

        `when`(repository.findById(movieId)).thenReturn(Optional.of(expectedEntity[0]))
        `when`(reviewMapper.fromEntityToDomain(expectedEntity[0].reviews!!.first())).thenReturn(expectedReview)

        val result = service.getMovieReviews(movieId)

        assertEquals(expectedCasts, result)
    }

    @Test
    @DisplayName("Deve retornar uma lista de `Video`")
    fun getMovieVideo_ShouldReturnListOfVideo_WhenMovieIdIsGiven() {
        val movieId = 1L
        val expectedVideo = Video(
            id = 1L,
            movieId = 1,
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
            createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
            updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
        )
        val expectedCasts = listOf(expectedVideo)

        `when`(repository.findById(movieId)).thenReturn(Optional.of(expectedEntity[0]))
        `when`(videoMapper.fromEntityToDomain(expectedEntity[0].videos!!.first())).thenReturn(expectedVideo)

        val result = service.getMovieVideos(movieId)

        assertEquals(expectedCasts, result)
    }

}