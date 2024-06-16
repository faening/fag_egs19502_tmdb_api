package com.github.faening.engsofttmdb.domain.service

import com.github.faening.engsofttmdb.data.model.db.CastEntity
import com.github.faening.engsofttmdb.data.model.db.MetadataEntity
import com.github.faening.engsofttmdb.data.repository.CastRepository
import com.github.faening.engsofttmdb.domain.mapper.CastMapper
import com.github.faening.engsofttmdb.domain.model.Cast
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime
import java.util.*

@SpringBootTest(properties = ["spring.profiles.active=test"])
class CastServiceTest {

    private lateinit var repository: CastRepository
    private lateinit var mapper: CastMapper
    private lateinit var service: CastService

    private lateinit var serviceSpy : CastService

    private var expectedEntity: MutableList<CastEntity> = mutableListOf()
    private var expectedValueObject: MutableList<Cast> = mutableListOf()

    @BeforeEach
    fun setup() {
        repository = Mockito.mock(CastRepository::class.java)
        mapper = Mockito.mock(CastMapper::class.java)
        service = CastService(repository, mapper)

        serviceSpy = Mockito.spy(service)

        expectedEntity = mutableListOf(
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
            ),
            CastEntity(
                id = 2L,
                adult = false,
                gender = 1,
                tmdbId = 1903874,
                knownForDepartment = "Acting",
                name = "cast_2",
                originalName = "cast_2",
                popularity = 22.113,
                profilePath = "/evjbbHM1bzA6Ma5Ptjwa4WkYkkj.jpg",
                castId = 4,
                character = "Anxiety (voice)",
                creditId = "654ce72cd4653700c4e31b8c",
                order = 0,
                metadata = MetadataEntity(
                    createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
                    updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
                )
            )
        )

        expectedValueObject = mutableListOf(
            Cast(
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
            ),
            Cast(
                id = 2L,
                adult = false,
                gender = 1,
                tmdbId = 1903874,
                knownForDepartment = "Acting",
                name = "cast_2",
                originalName = "cast_2",
                popularity = 22.113,
                profilePath = "/evjbbHM1bzA6Ma5Ptjwa4WkYkkj.jpg",
                castId = 4,
                character = "Anxiety (voice)",
                creditId = "654ce72cd4653700c4e31b8c",
                order = 0,
                createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
                updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
            )
        )

        Mockito.reset(repository, mapper)
    }

    @Test
    @DisplayName("Deve retornar uma lista de entidades do tipo `CastEntity`")
    fun getAllEntities_ShouldReturnListOfCastEntities_WhenRequestIsSuccessful() {
        val expected = expectedEntity
        `when`(repository.findAll()).thenReturn(expected)

        val actual = serviceSpy.getAllEntities()

        assertEquals(expected, actual, "Deve retornar uma lista de entidades do tipo `CastEntity`")
    }

    @Test
    @DisplayName("Deve retornar uma lista de `Cast`")
    fun getAll_ShouldReturnListOfCast_WhenRequestIsSuccessful() {
        val expectedEntities = expectedEntity
        val expected = expectedEntities.map { mapper.fromEntityToDomain(it) }
        `when`(repository.findAll()).thenReturn(expectedEntities)

        val actual = serviceSpy.getAll()

        assertEquals(expected, actual, "Deve retornar uma lista de `Cast`")
    }

    @Test
    @DisplayName("Deve retornar uma entidade `CastEntity` pelo ID")
    fun getEntityById_ShouldReturnCastEntity_WhenIdIsGiven() {
        val expected = expectedEntity.first()
        val id = expected.id
        `when`(id?.let { repository.findById(it) }).thenReturn(Optional.of(expected))

        val actual = id?.let { serviceSpy.getEntityById(it) }

        assertEquals(expected, actual, "Deve retornar uma entidade `CastEntity` pelo ID")
    }

    @Test
    @DisplayName("Deve retornar um `Cast` pelo ID")
    fun getById_ShouldReturnCast_WhenIdIsGiven() {
        val expectedEntity = expectedEntity.first()
        val id = expectedEntity.id
        val expected = mapper.fromEntityToDomain(expectedEntity)
        `when`(id?.let { repository.findById(it) }).thenReturn(Optional.of(expectedEntity))

        val actual = id?.let { serviceSpy.getById(it) }

        assertEquals(expected, actual, "Deve retornar um `Cast` pelo ID")
    }

    @Test
    @DisplayName("Deve salvar uma entidade `CastEntity`")
    fun saveEntity_ShouldSaveCastEntity_WhenEntityIsGiven() {
        val expected = expectedEntity.first()
        `when`(repository.save(expected)).thenReturn(expected)

        val actual = serviceSpy.saveEntity(expected)

        assertEquals(expected, actual, "Deve salvar uma entidade `CastEntity`")
    }

    @Test
    @DisplayName("Deve salvar uma lista de `CastEntity`")
    fun saveAllEntities_ShouldSaveListOfCastEntities_WhenListOfEntitiesIsGiven() {
        val expected = expectedEntity
        `when`(repository.saveAll(expected)).thenReturn(expected)

        val actual = serviceSpy.saveAllEntities(expected)

        assertEquals(expected, actual, "Deve salvar uma lista de `CastEntity`")
    }

    @Test
    @DisplayName("Deve salvar um `AuthorDetails`")
    fun save_ShouldSaveCast_WhenRequestIsGiven() {
        val request = expectedValueObject.first()
        val entity = mapper.fromDomainToEntity(request)

        @Suppress("SENSELESS_COMPARISON")
        if (entity != null) {
            val expected = mapper.fromEntityToDomain(entity)

            `when`(mapper.fromDomainToEntity(request)).thenReturn(entity)
            `when`(repository.save(entity)).thenReturn(entity)

            val actual = serviceSpy.save(request)

            assertEquals(expected, actual, "Deve salvar um `Cast`")
        }
    }

    @Test
    @DisplayName("Deve salvar uma lista de `Cast`")
    fun saveAll_ShouldSaveListOfCast_WhenListOfRequestsIsGiven() {
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

        assertEquals(expected, actual, "Deve salvar uma lista de `Cast`")
    }

    @Test
    @DisplayName("Deve atualizar uma entidade `CastEntity`")
    fun updateEntity_ShouldUpdateCastEntity_WhenEntityIsGiven() {
        val entity = expectedEntity.first()
        val expected = mapper.fromEntityToDomain(entity)
        `when`(repository.save(entity)).thenReturn(entity)
        `when`(mapper.fromEntityToDomain(entity)).thenReturn(expected)

        val actual = serviceSpy.updateEntity(entity)

        assertEquals(expected, actual, "Deve atualizar uma entidade `CastEntity`")
    }

    @Test
    @DisplayName("Deve atualizar um `Cast`")
    fun update_ShouldUpdateCast_WhenIdAndRequestAreGiven() {
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

            assertEquals(expected, actual, "Deve atualizar um `Cast`")
        }
    }

    @Test
    @DisplayName("Deve deletar uma entidade `CastEntity`")
    fun deleteEntity_ShouldDeleteCastEntity_WhenEntityIsGiven() {
        val entity = expectedEntity.first()
        val actual = serviceSpy.deleteEntity(entity)

        verify(repository).delete(entity)
        assertTrue(actual, "Deve deletar uma entidade `CastEntity`")
    }

    @Test
    @DisplayName("Deve deletar um `Cast` pelo ID")
    fun delete_ShouldDeleteCast_WhenIdIsGiven() {
        val id = 1L
        val entity = expectedEntity.first()

        `when`(repository.findById(id)).thenReturn(Optional.of(entity))
        `when`(serviceSpy.deleteEntity(entity)).thenReturn(true)

        val actual = serviceSpy.delete(id)

        verify(serviceSpy).getEntityById(id)
        verify(serviceSpy).deleteEntity(entity)
        assertTrue(actual, "Deve deletar um `Cast` pelo ID")
    }

}