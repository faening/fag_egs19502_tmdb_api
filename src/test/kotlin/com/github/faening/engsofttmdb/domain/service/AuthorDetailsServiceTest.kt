package com.github.faening.engsofttmdb.domain.service

import com.github.faening.engsofttmdb.data.model.db.AuthorDetailsEntity
import com.github.faening.engsofttmdb.data.model.db.MetadataEntity
import com.github.faening.engsofttmdb.data.repository.AuthorDetailsRepository
import com.github.faening.engsofttmdb.domain.mapper.AuthorDetailsMapper
import com.github.faening.engsofttmdb.domain.model.AuthorDetails
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
class AuthorDetailsServiceTest {

    private lateinit var repository: AuthorDetailsRepository
    private lateinit var mapper: AuthorDetailsMapper
    private lateinit var service: AuthorDetailsService

    private lateinit var serviceSpy : AuthorDetailsService

    private var expectedEntity: MutableList<AuthorDetailsEntity> = mutableListOf()
    private var expectedValueObject: MutableList<AuthorDetails> = mutableListOf()

    @BeforeEach
    fun setUp() {
        repository = Mockito.mock(AuthorDetailsRepository::class.java)
        mapper = Mockito.mock(AuthorDetailsMapper::class.java)
        service = AuthorDetailsService(repository, mapper)

        serviceSpy = Mockito.spy(service)

        expectedEntity = mutableListOf(
            AuthorDetailsEntity(
                id = 1L,
                name = "author_1",
                username = "author_1",
                avatarPath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
                rating = 8,
                metadata = MetadataEntity(
                    createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
                    updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
                )
            ),
            AuthorDetailsEntity(
                id = 2L,
                name = "author_2",
                username = "author_2",
                avatarPath = "/1kks3YnVkpyQxzw36CObFPvhL5f.jpg",
                rating = 6,
                metadata = MetadataEntity(
                    createdAt = LocalDateTime.parse("2024-06-15T19:15:17"),
                    updatedAt = LocalDateTime.parse("2024-06-15T19:15:17")
                )
            )
        )
        expectedValueObject = mutableListOf(
            AuthorDetails(
                id = 1L,
                name = "author_1",
                username = "author_1",
                avatarPath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
                rating = 8,
                createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
                updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
            ),
            AuthorDetails(
                id = 2L,
                name = "author_2",
                username = "author_2",
                avatarPath = "/1kks3YnVkpyQxzw36CObFPvhL5f.jpg",
                rating = 6,
                createdAt = LocalDateTime.parse("2024-06-15T19:15:17"),
                updatedAt = LocalDateTime.parse("2024-06-15T19:15:17")
            )
        )

        Mockito.reset(repository, mapper)
    }

    @Test
    @DisplayName("Deve retornar uma lista de entidades do tipo `AuthorDetailsEntity`")
    fun getAllEntities_ShouldReturnListOfAuthorDetailsEntities_WhenRequestIsSuccessful() {
        val expected = expectedEntity
        `when`(repository.findAll()).thenReturn(expected)

        val actual = serviceSpy.getAllEntities()

        assertEquals(expected, actual, "Deve retornar uma lista de entidades do tipo `AuthorDetailsEntity`")
    }

    @Test
    @DisplayName("Deve retornar uma lista de `AuthorDetails`")
    fun getAll_ShouldReturnListOfAuthorDetails_WhenRequestIsSuccessful() {
        val expectedEntities = expectedEntity
        val expected = expectedEntities.map { mapper.fromEntityToDomain(it) }
        `when`(repository.findAll()).thenReturn(expectedEntities)

        val actual = serviceSpy.getAll()

        assertEquals(expected, actual, "Deve retornar uma lista de `AuthorDetails`")
    }

    @Test
    @DisplayName("Deve retornar uma entidade `AuthorDetailsEntity` pelo ID")
    fun getEntityById_ShouldReturnAuthorDetailsEntity_WhenIdIsGiven() {
        val expected = expectedEntity.first()
        val id = expected.id
        `when`(id?.let { repository.findById(it) }).thenReturn(Optional.of(expected))

        val actual = id?.let { serviceSpy.getEntityById(it) }

        assertEquals(expected, actual, "Deve retornar uma entidade `AuthorDetailsEntity` pelo ID")
    }

    @Test
    @DisplayName("Deve retornar um `AuthorDetails` pelo ID")
    fun getById_ShouldReturnAuthorDetails_WhenIdIsGiven() {
        val expectedEntity = expectedEntity.first()
        val id = expectedEntity.id
        val expected = mapper.fromEntityToDomain(expectedEntity)
        `when`(id?.let { repository.findById(it) }).thenReturn(Optional.of(expectedEntity))

        val actual = id?.let { serviceSpy.getById(it) }

        assertEquals(expected, actual, "Deve retornar um `AuthorDetails` pelo ID")
    }

    @Test
    @DisplayName("Deve salvar uma entidade `AuthorDetailsEntity`")
    fun saveEntity_ShouldSaveAuthorDetailsEntity_WhenEntityIsGiven() {
        val expected = expectedEntity.first()
        `when`(repository.save(expected)).thenReturn(expected)

        val actual = serviceSpy.saveEntity(expected)

        assertEquals(expected, actual, "Deve salvar uma entidade `AuthorDetailsEntity`")
    }

    @Test
    @DisplayName("Deve salvar uma lista de `AuthorDetailsEntity`")
    fun saveAllEntities_ShouldSaveListOfAuthorDetailsEntities_WhenListOfEntitiesIsGiven() {
        val expected = expectedEntity
        `when`(repository.saveAll(expected)).thenReturn(expected)

        val actual = serviceSpy.saveAllEntities(expected)

        assertEquals(expected, actual, "Deve salvar uma lista de `AuthorDetailsEntity`")
    }

    @Test
    @DisplayName("Deve salvar um `AuthorDetails`")
    fun save_ShouldSaveAuthorDetails_WhenRequestIsGiven() {
        val request = expectedValueObject.first()
        val entity = mapper.fromDomainToEntity(request)

        @Suppress("SENSELESS_COMPARISON")
        if (entity != null) {
            val expected = mapper.fromEntityToDomain(entity)

            `when`(mapper.fromDomainToEntity(request)).thenReturn(entity)
            `when`(repository.save(entity)).thenReturn(entity)

            val actual = serviceSpy.save(request)

            assertEquals(expected, actual, "Deve salvar um `AuthorDetails`")
        }
    }

    @Test
    @DisplayName("Deve salvar uma lista de `AuthorDetails`")
    fun saveAll_ShouldSaveListOfAuthorDetails_WhenListOfRequestsIsGiven() {
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

        assertEquals(expected, actual, "Deve salvar uma lista de `AuthorDetails`")
    }

    @Test
    @DisplayName("Deve atualizar uma entidade `AuthorDetailsEntity`")
    fun updateEntity_ShouldUpdateAuthorDetailsEntity_WhenEntityIsGiven() {
        val entity = expectedEntity.first()
        val expected = mapper.fromEntityToDomain(entity)
        `when`(repository.save(entity)).thenReturn(entity)
        `when`(mapper.fromEntityToDomain(entity)).thenReturn(expected)

        val actual = serviceSpy.updateEntity(entity)

        assertEquals(expected, actual, "Deve atualizar uma entidade `AuthorDetailsEntity`")
    }

    @Test
    @DisplayName("Deve atualizar um `AuthorDetails`")
    fun update_ShouldUpdateAuthorDetails_WhenIdAndRequestAreGiven() {
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

            assertEquals(expected, actual, "Deve atualizar um `AuthorDetails`")
        }
    }

    @Test
    @DisplayName("Deve deletar uma entidade `AuthorDetailsEntity`")
    fun deleteEntity_ShouldDeleteAuthorDetailsEntity_WhenEntityIsGiven() {
        val entity = expectedEntity.first()
        val actual = serviceSpy.deleteEntity(entity)

        verify(repository).delete(entity)
        assertTrue(actual, "Deve deletar uma entidade `AuthorDetailsEntity`")
    }

    @Test
    @DisplayName("Deve deletar um `AuthorDetails` pelo ID")
    fun delete_ShouldDeleteAuthorDetails_WhenIdIsGiven() {
        val id = 1L
        val entity: AuthorDetailsEntity = expectedEntity.first()

        `when`(repository.findById(id)).thenReturn(Optional.of(entity))
        `when`(serviceSpy.deleteEntity(entity)).thenReturn(true)

        val actual = serviceSpy.delete(id)

        verify(serviceSpy).getEntityById(id)
        verify(serviceSpy).deleteEntity(entity)
        assertTrue(actual, "Deve deletar um `AuthorDetails` pelo ID")
    }

}