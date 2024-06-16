package com.github.faening.engsofttmdb.domain.service

import com.github.faening.engsofttmdb.data.model.db.GenreEntity
import com.github.faening.engsofttmdb.data.model.db.MetadataEntity
import com.github.faening.engsofttmdb.data.repository.GenreRepository
import com.github.faening.engsofttmdb.domain.mapper.GenreMapper
import com.github.faening.engsofttmdb.domain.model.Genre
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
import java.time.LocalDateTime
import java.util.*

@SpringBootTest
@ActiveProfiles("test")
class GenreServiceTest {

    private lateinit var repository: GenreRepository
    private lateinit var mapper: GenreMapper
    private lateinit var service: GenreService

    private lateinit var serviceSpy: GenreService

    private var expectedEntity: MutableList<GenreEntity> = mutableListOf()
    private var expectedValueObject: MutableList<Genre> = mutableListOf()

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(GenreRepository::class.java)
        mapper = Mockito.mock(GenreMapper::class.java)
        service = GenreService(repository, mapper)

        serviceSpy = Mockito.spy(service)

        expectedEntity = mutableListOf(
            GenreEntity(
                id = 1L,
                tmdbId = 27,
                name = "Ação",
                movies = emptySet(),
                metadata = MetadataEntity(
                    createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
                    updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
                )
            ),
            GenreEntity(
                id = 2L,
                tmdbId = 13,
                name = "Aventura",
                movies = emptySet(),
                metadata = MetadataEntity(
                    createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
                    updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
                )
            ),
        )

        expectedValueObject = mutableListOf(
            Genre(
                id = 1L,
                tmdbId = 27,
                name = "Ação",
                createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
                updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
            ),
            Genre(
                id = 2L,
                tmdbId = 13,
                name = "Aventura",
                createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
                updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
            )
        )

        Mockito.reset(repository, mapper)
    }

    @Test
    @DisplayName("Deve retornar uma lista de entidades do tipo `GenreEntity`")
    fun getAllEntities_ShouldReturnListOfGenreEntities_WhenRequestIsSuccessful() {
        val expected = expectedEntity
        `when`(repository.findAll()).thenReturn(expected)

        val actual = serviceSpy.getAllEntities()

        assertEquals(expected, actual, "Deve retornar uma lista de entidades do tipo `GenreEntity`")
    }

    @Test
    @DisplayName("Deve retornar uma lista de `Genre`")
    fun getAll_ShouldReturnListOfGenre_WhenRequestIsSuccessful() {
        val expectedEntities = expectedEntity
        val expected = expectedEntities.map { mapper.fromEntityToDomain(it) }
        `when`(repository.findAll()).thenReturn(expectedEntities)

        val actual = serviceSpy.getAll()

        assertEquals(expected, actual, "Deve retornar uma lista de `Genre`")
    }

    @Test
    @DisplayName("Deve retornar uma entidade `GenreEntity` pelo ID")
    fun getEntityById_ShouldReturnGenreEntity_WhenIdIsGiven() {
        val expected = expectedEntity.first()
        val id = expected.id
        `when`(id?.let { repository.findById(it) }).thenReturn(Optional.of(expected))

        val actual = id?.let { serviceSpy.getEntityById(it) }

        assertEquals(expected, actual, "Deve retornar uma entidade `GenreEntity` pelo ID")
    }

    @Test
    @DisplayName("Deve retornar um `Genre` pelo ID")
    fun getById_ShouldReturnGenre_WhenIdIsGiven() {
        val expectedEntity = expectedEntity.first()
        val id = expectedEntity.id
        val expected = mapper.fromEntityToDomain(expectedEntity)
        `when`(id?.let { repository.findById(it) }).thenReturn(Optional.of(expectedEntity))

        val actual = id?.let { serviceSpy.getById(it) }

        assertEquals(expected, actual, "Deve retornar um `Genre` pelo ID")
    }

    @Test
    @DisplayName("Deve salvar uma entidade `GenreEntity`")
    fun saveEntity_ShouldSaveGenreEntity_WhenEntityIsGiven() {
        val expected = expectedEntity.first()
        `when`(repository.save(expected)).thenReturn(expected)

        val actual = serviceSpy.saveEntity(expected)

        assertEquals(expected, actual, "Deve salvar uma entidade `GenreEntity`")
    }

    @Test
    @DisplayName("Deve salvar uma lista de `GenreEntity`")
    fun saveAllEntities_ShouldSaveListOfGenreEntities_WhenListOfEntitiesIsGiven() {
        val expected = expectedEntity
        `when`(repository.saveAll(expected)).thenReturn(expected)

        val actual = serviceSpy.saveAllEntities(expected)

        assertEquals(expected, actual, "Deve salvar uma lista de `GenreEntity`")
    }

    @Test
    @DisplayName("Deve salvar um `Genre`")
    fun save_ShouldSaveGenre_WhenRequestIsGiven() {
        val request = expectedValueObject.first()
        val entity = mapper.fromDomainToEntity(request)

        @Suppress("SENSELESS_COMPARISON")
        if (entity != null) {
            val expected = mapper.fromEntityToDomain(entity)

            `when`(mapper.fromDomainToEntity(request)).thenReturn(entity)
            `when`(repository.save(entity)).thenReturn(entity)

            val actual = serviceSpy.save(request)

            assertEquals(expected, actual, "Deve salvar um `Genre`")
        }
    }

    @Test
    @DisplayName("Deve salvar uma lista de `Genre`")
    fun saveAll_ShouldSaveListOfGenre_WhenListOfRequestsIsGiven() {
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

        assertEquals(expected, actual, "Deve salvar uma lista de `Genre`")
    }

    @Test
    @DisplayName("Deve atualizar uma entidade `GenreEntity`")
    fun updateEntity_ShouldUpdateGenreEntity_WhenEntityIsGiven() {
        val entity = expectedEntity.first()
        val expected = mapper.fromEntityToDomain(entity)
        `when`(repository.save(entity)).thenReturn(entity)
        `when`(mapper.fromEntityToDomain(entity)).thenReturn(expected)

        val actual = serviceSpy.updateEntity(entity)

        assertEquals(expected, actual, "Deve atualizar uma entidade `GenreEntity`")
    }

    @Test
    @DisplayName("Deve atualizar um `Genre`")
    fun update_ShouldUpdateGenre_WhenIdAndRequestAreGiven() {
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

            assertEquals(expected, actual, "Deve atualizar um `Genre`")
        }
    }

    @Test
    @DisplayName("Deve deletar uma entidade `GenreEntity`")
    fun deleteEntity_ShouldDeleteGenreEntity_WhenEntityIsGiven() {
        val entity = expectedEntity.first()
        val actual = serviceSpy.deleteEntity(entity)

        verify(repository).delete(entity)
        assertTrue(actual, "Deve deletar uma entidade `GenreEntitys`")
    }

    @Test
    @DisplayName("Deve deletar um `Genre` pelo ID")
    fun delete_ShouldDeleteGenre_WhenIdIsGiven() {
        val id = 1L
        val entity = expectedEntity.first()

        `when`(repository.findById(id)).thenReturn(Optional.of(entity))
        `when`(serviceSpy.deleteEntity(entity)).thenReturn(true)

        val actual = serviceSpy.delete(id)

        verify(serviceSpy).getEntityById(id)
        verify(serviceSpy).deleteEntity(entity)
        assertTrue(actual, "Deve deletar um `Genre` pelo ID")
    }

}