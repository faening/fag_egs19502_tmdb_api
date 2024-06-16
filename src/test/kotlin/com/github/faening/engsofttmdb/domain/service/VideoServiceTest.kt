package com.github.faening.engsofttmdb.domain.service

import com.github.faening.engsofttmdb.data.model.db.GenreEntity
import com.github.faening.engsofttmdb.data.model.db.MetadataEntity
import com.github.faening.engsofttmdb.data.model.db.MovieEntity
import com.github.faening.engsofttmdb.data.model.db.VideoEntity
import com.github.faening.engsofttmdb.data.repository.VideoRepository
import com.github.faening.engsofttmdb.domain.mapper.VideoMapper
import com.github.faening.engsofttmdb.domain.model.Video
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@SpringBootTest(properties = ["spring.profiles.active=test"])
class VideoServiceTest {

    private lateinit var repository: VideoRepository
    private lateinit var mapper: VideoMapper
    private lateinit var service: VideoService

    private lateinit var serviceSpy: VideoService

    private var expectedEntity: MutableList<VideoEntity> = mutableListOf()
    private var expectedValueObject: MutableList<Video> = mutableListOf()

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(VideoRepository::class.java)
        mapper = Mockito.mock(VideoMapper::class.java)
        service = VideoService(repository, mapper)

        serviceSpy = Mockito.spy(service)

        expectedEntity = mutableListOf(
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
            ),
            VideoEntity(
                id = 2L,
                movie = MovieEntity(
                    id = 2L,
                    tmdbId = 573435,
                    adult = false,
                    backdropPath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
                    genres = hashSetOf(
                        GenreEntity(
                            id = 2L,
                            tmdbId = 16,
                            name = "Romance",
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
                iso6391 = "pt",
                iso31661 = "BR",
                name = "movie_2",
                videoKey = "uskbXdnbcvg",
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
        )

        expectedValueObject = mutableListOf(
            Video(
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
            ),
            Video(
                id = 2L,
                movieId = 2,
                iso6391 = "pt",
                iso31661 = "BR",
                name = "movie_2",
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
        )

        Mockito.reset(repository, mapper)
    }

    @Test
    @DisplayName("Deve retornar uma lista de entidades do tipo `VideoEntity`")
    fun getAllEntities_ShouldReturnListOfVideoEntities_WhenRequestIsSuccessful() {
        val expected = expectedEntity
        `when`(repository.findAll()).thenReturn(expected)

        val actual = serviceSpy.getAllEntities()

        assertEquals(expected, actual, "Deve retornar uma lista de entidades do tipo `VideoEntity`")
    }

    @Test
    @DisplayName("Deve retornar uma lista de `Video`")
    fun getAll_ShouldReturnListOfVideo_WhenRequestIsSuccessful() {
        val expectedEntities = expectedEntity
        val expected = expectedEntities.map { mapper.fromEntityToDomain(it) }
        `when`(repository.findAll()).thenReturn(expectedEntities)

        val actual = serviceSpy.getAll()

        assertEquals(expected, actual, "Deve retornar uma lista de `Video`")
    }

    @Test
    @DisplayName("Deve retornar uma entidade `VideoEntity` pelo ID")
    fun getEntityById_ShouldReturnVideoEntity_WhenIdIsGiven() {
        val expected = expectedEntity.first()
        val id = expected.id
        `when`(id?.let { repository.findById(it) }).thenReturn(Optional.of(expected))

        val actual = id?.let { serviceSpy.getEntityById(it) }

        assertEquals(expected, actual, "Deve retornar uma entidade `VideoEntity` pelo ID")
    }

    @Test
    @DisplayName("Deve retornar um `Video` pelo ID")
    fun getById_ShouldReturnVideo_WhenIdIsGiven() {
        val expectedEntity = expectedEntity.first()
        val id = expectedEntity.id
        val expected = mapper.fromEntityToDomain(expectedEntity)
        `when`(id?.let { repository.findById(it) }).thenReturn(Optional.of(expectedEntity))

        val actual = id?.let { serviceSpy.getById(it) }

        assertEquals(expected, actual, "Deve retornar um `Video` pelo ID")
    }

    @Test
    @DisplayName("Deve salvar uma entidade `VideoEntity`")
    fun saveEntity_ShouldSaveVideoEntity_WhenEntityIsGiven() {
        val expected = expectedEntity.first()
        `when`(repository.save(expected)).thenReturn(expected)

        val actual = serviceSpy.saveEntity(expected)

        assertEquals(expected, actual, "Deve salvar uma entidade `VideoEntity`")
    }

    @Test
    @DisplayName("Deve salvar uma lista de `VideoEntity`")
    fun saveAllEntities_ShouldSaveListOfVideoEntities_WhenListOfEntitiesIsGiven() {
        val expected = expectedEntity
        `when`(repository.saveAll(expected)).thenReturn(expected)

        val actual = serviceSpy.saveAllEntities(expected)

        assertEquals(expected, actual, "Deve salvar uma lista de `VideoEntity`")
    }

    @Test
    @DisplayName("Deve salvar um `Video`")
    fun save_ShouldSaveVideo_WhenRequestIsGiven() {
        val request = expectedValueObject.first()
        val entity = mapper.fromDomainToEntity(request)

        @Suppress("SENSELESS_COMPARISON")
        if (entity != null) {
            val expected = mapper.fromEntityToDomain(entity)

            `when`(mapper.fromDomainToEntity(request)).thenReturn(entity)
            `when`(repository.save(entity)).thenReturn(entity)

            val actual = serviceSpy.save(request)

            assertEquals(expected, actual, "Deve salvar um `Video`")
        }
    }

    @Test
    @DisplayName("Deve salvar uma lista de `Video`")
    fun saveAll_ShouldSaveListOfVideo_WhenListOfRequestsIsGiven() {
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

        assertEquals(expected, actual, "Deve salvar uma lista de `Video`")
    }

    @Test
    @DisplayName("Deve atualizar uma entidade `VideoEntity`")
    fun updateEntity_ShouldUpdateVideoEntity_WhenEntityIsGiven() {
        val entity = expectedEntity.first()
        val expected = mapper.fromEntityToDomain(entity)
        `when`(repository.save(entity)).thenReturn(entity)
        `when`(mapper.fromEntityToDomain(entity)).thenReturn(expected)

        val actual = serviceSpy.updateEntity(entity)

        assertEquals(expected, actual, "Deve atualizar uma entidade `VideoEntity`")
    }

    @Test
    @DisplayName("Deve atualizar um `Video`")
    fun update_ShouldUpdateVideo_WhenIdAndRequestAreGiven() {
        val id = 1L
        val request = expectedValueObject.first()
        val entity = mapper.fromDomainToEntity(request)

        @Suppress("SENSELESS_COMPARISON")
        if (entity != null) {
            val mergedEntity = entity.copy(name = "author_updated")
            val expected = mapper.fromEntityToDomain(mergedEntity)

            `when`(serviceSpy.getEntityById(id)).thenReturn(entity)
            `when`(mapper.mergeEntityAndRequest(entity, request)).thenReturn(mergedEntity)
            `when`(serviceSpy.updateEntity(mergedEntity)).thenReturn(expected)

            val actual = serviceSpy.update(id, request)

            assertEquals(expected, actual, "Deve atualizar um `Video`")
        }
    }

    @Test
    @DisplayName("Deve deletar uma entidade `VideoEntity`")
    fun deleteEntity_ShouldDeleteVideoEntity_WhenEntityIsGiven() {
        val entity = expectedEntity.first()
        val actual = serviceSpy.deleteEntity(entity)

        verify(repository).delete(entity)
        assertTrue(actual, "Deve deletar uma entidade `VideoEntitys`")
    }

    @Test
    @DisplayName("Deve deletar um `Video` pelo ID")
    fun delete_ShouldDeleteVideo_WhenIdIsGiven() {
        val id = 1L
        val entity = expectedEntity.first()

        `when`(repository.findById(id)).thenReturn(Optional.of(entity))
        `when`(serviceSpy.deleteEntity(entity)).thenReturn(true)

        val actual = serviceSpy.delete(id)

        verify(serviceSpy).getEntityById(id)
        verify(serviceSpy).deleteEntity(entity)
        assertTrue(actual, "Deve deletar um `Video` pelo ID")
    }

}