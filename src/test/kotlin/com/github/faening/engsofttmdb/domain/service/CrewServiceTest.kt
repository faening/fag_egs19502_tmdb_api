package com.github.faening.engsofttmdb.domain.service

import com.github.faening.engsofttmdb.data.model.db.CrewEntity
import com.github.faening.engsofttmdb.data.model.db.MetadataEntity
import com.github.faening.engsofttmdb.data.repository.CrewRepository
import com.github.faening.engsofttmdb.domain.mapper.CrewMapper
import com.github.faening.engsofttmdb.domain.model.Crew
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
class CrewServiceTest {

    private lateinit var repository: CrewRepository
    private lateinit var mapper: CrewMapper
    private lateinit var service: CrewService

    private lateinit var serviceSpy : CrewService

    private var expectedEntity: MutableList<CrewEntity> = mutableListOf()
    private var expectedValueObject: MutableList<Crew> = mutableListOf()

    @BeforeEach
    fun setup() {
        repository = Mockito.mock(CrewRepository::class.java)
        mapper = Mockito.mock(CrewMapper::class.java)
        service = CrewService(repository, mapper)

        serviceSpy = Mockito.spy(service)

        expectedEntity = mutableListOf(
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
            ),
            CrewEntity(
                id = 2L,
                adult = false,
                gender = 1,
                tmdbId = 8103,
                knownForDepartment = "Production",
                name = "crew_2",
                originalName = "crew_2",
                popularity = 26.804,
                profilePath = "/bqojcGtjzHDiUWgcpjbgY1KEyOx.jpg",
                creditId = "631bd73351c01f007dcf1b97",
                department = "Production",
                job = "Producer",
                metadata = MetadataEntity(
                    createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
                    updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
                )
            ),
        )

        expectedValueObject = mutableListOf(
            Crew(
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
            ),
            Crew(
                id = 2L,
                adult = false,
                gender = 1,
                tmdbId = 8103,
                knownForDepartment = "Production",
                name = "crew_2",
                originalName = "crew_2",
                popularity = 26.804,
                profilePath = "/bqojcGtjzHDiUWgcpjbgY1KEyOx.jpg",
                creditId = "631bd73351c01f007dcf1b97",
                department = "Production",
                job = "Producer",
                createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
                updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
            )
        )

        Mockito.reset(repository, mapper)
    }

    @Test
    @DisplayName("Deve retornar uma lista de entidades do tipo `CrewEntity`")
    fun getAllEntities_ShouldReturnListOfCrewEntities_WhenRequestIsSuccessful() {
        val expected = expectedEntity
        `when`(repository.findAll()).thenReturn(expected)

        val actual = serviceSpy.getAllEntities()

        assertEquals(expected, actual, "Deve retornar uma lista de entidades do tipo `CrewEntity`")
    }

    @Test
    @DisplayName("Deve retornar uma lista de `Crew`")
    fun getAll_ShouldReturnListOfCrew_WhenRequestIsSuccessful() {
        val expectedEntities = expectedEntity
        val expected = expectedEntities.map { mapper.fromEntityToDomain(it) }
        `when`(repository.findAll()).thenReturn(expectedEntities)

        val actual = serviceSpy.getAll()

        assertEquals(expected, actual, "Deve retornar uma lista de `Crew`")
    }

    @Test
    @DisplayName("Deve retornar uma entidade `CrewEntity` pelo ID")
    fun getEntityById_ShouldReturnCrewEntity_WhenIdIsGiven() {
        val expected = expectedEntity.first()
        val id = expected.id
        `when`(id?.let { repository.findById(it) }).thenReturn(Optional.of(expected))

        val actual = id?.let { serviceSpy.getEntityById(it) }

        assertEquals(expected, actual, "Deve retornar uma entidade `CrewEntity` pelo ID")
    }

    @Test
    @DisplayName("Deve retornar um `Crew` pelo ID")
    fun getById_ShouldReturnCrew_WhenIdIsGiven() {
        val expectedEntity = expectedEntity.first()
        val id = expectedEntity.id
        val expected = mapper.fromEntityToDomain(expectedEntity)
        `when`(id?.let { repository.findById(it) }).thenReturn(Optional.of(expectedEntity))

        val actual = id?.let { serviceSpy.getById(it) }

        assertEquals(expected, actual, "Deve retornar um `Crew` pelo ID")
    }

    @Test
    @DisplayName("Deve salvar uma entidade `CrewEntity`")
    fun saveEntity_ShouldSaveCrewEntity_WhenEntityIsGiven() {
        val expected = expectedEntity.first()
        `when`(repository.save(expected)).thenReturn(expected)

        val actual = serviceSpy.saveEntity(expected)

        assertEquals(expected, actual, "Deve salvar uma entidade `CrewEntity`")
    }

    @Test
    @DisplayName("Deve salvar uma lista de `CrewEntity`")
    fun saveAllEntities_ShouldSaveListOfCrewEntities_WhenListOfEntitiesIsGiven() {
        val expected = expectedEntity
        `when`(repository.saveAll(expected)).thenReturn(expected)

        val actual = serviceSpy.saveAllEntities(expected)

        assertEquals(expected, actual, "Deve salvar uma lista de `CrewEntity`")
    }

    @Test
    @DisplayName("Deve salvar um `Crew`")
    fun save_ShouldSaveCrew_WhenRequestIsGiven() {
        val request = expectedValueObject.first()
        val entity = mapper.fromDomainToEntity(request)

        @Suppress("SENSELESS_COMPARISON")
        if (entity != null) {
            val expected = mapper.fromEntityToDomain(entity)

            `when`(mapper.fromDomainToEntity(request)).thenReturn(entity)
            `when`(repository.save(entity)).thenReturn(entity)

            val actual = serviceSpy.save(request)

            assertEquals(expected, actual, "Deve salvar um `Crew`")
        }
    }

    @Test
    @DisplayName("Deve salvar uma lista de `Crew`")
    fun saveAll_ShouldSaveListOfCrew_WhenListOfRequestsIsGiven() {
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

        assertEquals(expected, actual, "Deve salvar uma lista de `Crew`")
    }

    @Test
    @DisplayName("Deve atualizar uma entidade `CrewEntity`")
    fun updateEntity_ShouldUpdateCrewEntity_WhenEntityIsGiven() {
        val entity = expectedEntity.first()
        val expected = mapper.fromEntityToDomain(entity)
        `when`(repository.save(entity)).thenReturn(entity)
        `when`(mapper.fromEntityToDomain(entity)).thenReturn(expected)

        val actual = serviceSpy.updateEntity(entity)

        assertEquals(expected, actual, "Deve atualizar uma entidade `CrewEntity`")
    }

    @Test
    @DisplayName("Deve atualizar um `Crew`")
    fun update_ShouldUpdateCrew_WhenIdAndRequestAreGiven() {
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

            assertEquals(expected, actual, "Deve atualizar um `Crew`")
        }
    }

    @Test
    @DisplayName("Deve deletar uma entidade `CrewEntity`")
    fun deleteEntity_ShouldDeleteCrewEntity_WhenEntityIsGiven() {
        val entity = expectedEntity.first()
        val actual = serviceSpy.deleteEntity(entity)

        verify(repository).delete(entity)
        assertTrue(actual, "Deve deletar uma entidade `CrewEntity`")
    }

    @Test
    @DisplayName("Deve deletar um `Crew` pelo ID")
    fun delete_ShouldDeleteCrew_WhenIdIsGiven() {
        val id = 1L
        val entity = expectedEntity.first()

        `when`(repository.findById(id)).thenReturn(Optional.of(entity))
        `when`(serviceSpy.deleteEntity(entity)).thenReturn(true)

        val actual = serviceSpy.delete(id)

        verify(serviceSpy).getEntityById(id)
        verify(serviceSpy).deleteEntity(entity)
        assertTrue(actual, "Deve deletar um `Crew` pelo ID")
    }











}