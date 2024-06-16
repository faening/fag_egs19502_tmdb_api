package com.github.faening.engsofttmdb.domain.service

import com.github.faening.engsofttmdb.data.model.db.*
import com.github.faening.engsofttmdb.data.repository.ReviewRepository
import com.github.faening.engsofttmdb.domain.mapper.ReviewMapper
import com.github.faening.engsofttmdb.domain.model.AuthorDetails
import com.github.faening.engsofttmdb.domain.model.Review
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
class ReviewServiceTest {

    private lateinit var repository: ReviewRepository
    private lateinit var mapper: ReviewMapper
    private lateinit var service: ReviewService

    private lateinit var serviceSpy: ReviewService

    private var expectedEntity: MutableList<ReviewEntity> = mutableListOf()
    private var expectedValueObject: MutableList<Review> = mutableListOf()

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(ReviewRepository::class.java)
        mapper = Mockito.mock(ReviewMapper::class.java)
        service = ReviewService(repository, mapper)

        serviceSpy = Mockito.spy(service)

        expectedEntity = mutableListOf(
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
            ),
            ReviewEntity(
                id = 2L,
                movie = MovieEntity(
                    id = 2L,
                    tmdbId = 653346,
                    adult = false,
                    backdropPath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
                    genres = hashSetOf(
                        GenreEntity(
                            id = 2L,
                            tmdbId = 15,
                            name = "Aventura",
                            movies = emptySet(),
                            metadata = MetadataEntity(
                                createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
                                updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
                            )
                        )
                    ),
                    originalLanguage = "en",
                    originalTitle = "movie_2",
                    overview = "overview_2",
                    popularity = 10.0,
                    posterPath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
                    releaseDate = LocalDate.parse("2024-06-15"),
                    title = "movie_2",
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
                author = "author_2",
                authorDetails = AuthorDetailsEntity(
                    id = 2L,
                    name = "author_2",
                    username = "author_2",
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
            ),
        )

        expectedValueObject = mutableListOf(
            Review(
                id = 1L,
                movieId = 1L,
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
            ),
            Review(
                id = 2L,
                movieId = 2L,
                author = "author_2",
                authorDetails = AuthorDetails(
                    id = 2L,
                    name = "author_2",
                    username = "author_2",
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
        )

        Mockito.reset(repository, mapper)
    }

    @Test
    @DisplayName("Deve retornar uma lista de entidades do tipo `ReviewEntity`")
    fun getAllEntities_ShouldReturnListOfReviewEntities_WhenRequestIsSuccessful() {
        val expected = expectedEntity
        `when`(repository.findAll()).thenReturn(expected)

        val actual = serviceSpy.getAllEntities()

        assertEquals(expected, actual, "Deve retornar uma lista de entidades do tipo `ReviewEntity`")
    }

    @Test
    @DisplayName("Deve retornar uma lista de `Review`")
    fun getAll_ShouldReturnListOfReview_WhenRequestIsSuccessful() {
        val expectedEntities = expectedEntity
        val expected = expectedEntities.map { mapper.fromEntityToDomain(it) }
        `when`(repository.findAll()).thenReturn(expectedEntities)

        val actual = serviceSpy.getAll()

        assertEquals(expected, actual, "Deve retornar uma lista de `Review`")
    }

    @Test
    @DisplayName("Deve retornar uma entidade `ReviewEntity` pelo ID")
    fun getEntityById_ShouldReturnReviewEntity_WhenIdIsGiven() {
        val expected = expectedEntity.first()
        val id = expected.id
        `when`(id?.let { repository.findById(it) }).thenReturn(Optional.of(expected))

        val actual = id?.let { serviceSpy.getEntityById(it) }

        assertEquals(expected, actual, "Deve retornar uma entidade `ReviewEntity` pelo ID")
    }

    @Test
    @DisplayName("Deve retornar um `Review` pelo ID")
    fun getById_ShouldReturnReview_WhenIdIsGiven() {
        val expectedEntity = expectedEntity.first()
        val id = expectedEntity.id
        val expected = mapper.fromEntityToDomain(expectedEntity)
        `when`(id?.let { repository.findById(it) }).thenReturn(Optional.of(expectedEntity))

        val actual = id?.let { serviceSpy.getById(it) }

        assertEquals(expected, actual, "Deve retornar um `Review` pelo ID")
    }

    @Test
    @DisplayName("Deve salvar uma entidade `ReviewEntity`")
    fun saveEntity_ShouldSaveReviewEntity_WhenEntityIsGiven() {
        val expected = expectedEntity.first()
        `when`(repository.save(expected)).thenReturn(expected)

        val actual = serviceSpy.saveEntity(expected)

        assertEquals(expected, actual, "Deve salvar uma entidade `ReviewEntity`")
    }

    @Test
    @DisplayName("Deve salvar uma lista de `ReviewEntity`")
    fun saveAllEntities_ShouldSaveListOfReviewEntities_WhenListOfEntitiesIsGiven() {
        val expected = expectedEntity
        `when`(repository.saveAll(expected)).thenReturn(expected)

        val actual = serviceSpy.saveAllEntities(expected)

        assertEquals(expected, actual, "Deve salvar uma lista de `ReviewEntity`")
    }

    @Test
    @DisplayName("Deve salvar um `Review`")
    fun save_ShouldSaveReview_WhenRequestIsGiven() {
        val request = expectedValueObject.first()
        val entity = mapper.fromDomainToEntity(request)

        @Suppress("SENSELESS_COMPARISON")
        if (entity != null) {
            val expected = mapper.fromEntityToDomain(entity)

            `when`(mapper.fromDomainToEntity(request)).thenReturn(entity)
            `when`(repository.save(entity)).thenReturn(entity)

            val actual = serviceSpy.save(request)

            assertEquals(expected, actual, "Deve salvar um `Review`")
        }
    }

    @Test
    @DisplayName("Deve salvar uma lista de `Review`")
    fun saveAll_ShouldSaveListOfReview_WhenListOfRequestsIsGiven() {
        val request = expectedValueObject
        val entities = request.map { mapper.fromDomainToEntity(it) }
        val expected = entities.map { mapper.fromEntityToDomain(it) }

        for (i in request.indices) {
            `when`(mapper.fromDomainToEntity(request[i])).thenReturn(entities[i])
        }

        `when`(repository.saveAll(entities)).thenReturn(entities)

        for (i in entities.indices) {
            `when`(mapper.fromEntityToDomain(entities[i])).thenReturn(expected[i])
        }

        val actual = serviceSpy.saveAll(request)

        assertEquals(expected, actual, "Deve salvar uma lista de `Review`")
    }

    @Test
    @DisplayName("Deve atualizar uma entidade `ReviewEntity`")
    fun updateEntity_ShouldUpdateReviewEntity_WhenEntityIsGiven() {
        val entity = expectedEntity.first()
        val expected = mapper.fromEntityToDomain(entity)
        `when`(repository.save(entity)).thenReturn(entity)
        `when`(mapper.fromEntityToDomain(entity)).thenReturn(expected)

        val actual = serviceSpy.updateEntity(entity)

        assertEquals(expected, actual, "Deve atualizar uma entidade `ReviewEntity`")
    }

    @Test
    @DisplayName("Deve atualizar um `Review`")
    fun update_ShouldUpdateReview_WhenIdAndRequestAreGiven() {
        val id = 1L
        val request = expectedValueObject.first()
        val entity = mapper.fromDomainToEntity(request)

        @Suppress("SENSELESS_COMPARISON")
        if (entity != null) {
            val mergedEntity = entity.copy(author = "author_updated")
            val expected = mapper.fromEntityToDomain(mergedEntity)

            `when`(serviceSpy.getEntityById(id)).thenReturn(entity)
            `when`(mapper.mergeEntityAndRequest(entity, request)).thenReturn(mergedEntity)
            `when`(serviceSpy.updateEntity(mergedEntity)).thenReturn(expected)

            val actual = serviceSpy.update(id, request)

            assertEquals(expected, actual, "Deve atualizar um `Review`")
        }
    }

    @Test
    @DisplayName("Deve deletar uma entidade `ReviewEntity`")
    fun deleteEntity_ShouldDeleteReviewEntity_WhenEntityIsGiven() {
        val entity = expectedEntity.first()
        val actual = serviceSpy.deleteEntity(entity)

        verify(repository).delete(entity)
        assertTrue(actual, "Deve deletar uma entidade `ReviewEntitys`")
    }

    @Test
    @DisplayName("Deve deletar um `Review` pelo ID")
    fun delete_ShouldDeleteReview_WhenIdIsGiven() {
        val id = 1L
        val entity = expectedEntity.first()

        `when`(repository.findById(id)).thenReturn(Optional.of(entity))
        `when`(serviceSpy.deleteEntity(entity)).thenReturn(true)

        val actual = serviceSpy.delete(id)

        verify(serviceSpy).getEntityById(id)
        verify(serviceSpy).deleteEntity(entity)
        assertTrue(actual, "Deve deletar um `Review` pelo ID")
    }

}