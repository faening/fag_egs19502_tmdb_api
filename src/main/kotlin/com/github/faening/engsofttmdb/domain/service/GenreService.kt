package com.github.faening.engsofttmdb.domain.service

import com.github.faening.engsofttmdb.data.model.db.GenreEntity
import com.github.faening.engsofttmdb.data.repository.GenreRepository
import com.github.faening.engsofttmdb.domain.contract.BaseService
import com.github.faening.engsofttmdb.domain.mapper.GenreMapper
import com.github.faening.engsofttmdb.domain.model.Genre
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GenreService @Autowired constructor(
    private val repository: GenreRepository,
    private val mapper: GenreMapper
) : BaseService<GenreEntity, Genre, Genre> {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun getAllEntities(): List<GenreEntity> {
        return repository.findAll()
    }

    @Transactional
    override fun getAll(): List<Genre> {
        val entities = repository.findAll()
        val genresMapped = entities.map { genre -> mapper.fromEntityToDomain(genre) }
        return genresMapped
    }

    override fun getEntityById(id: Long): GenreEntity {
        return repository.findById(id).orElseThrow { throw RuntimeException("Genre not found") }
    }

    @Transactional
    override fun getById(id: Long): Genre {
        val entity = getEntityById(id)
        val genre = mapper.fromEntityToDomain(entity)
        return genre
    }

    override fun saveEntity(entity: GenreEntity): GenreEntity {
        if (entity.id != null && entityManager.contains(entity).not()) {
            return repository.save(entityManager.merge(entity))
        }
        return repository.save(entity)
    }

    override fun saveAllEntities(entities: List<GenreEntity>): List<GenreEntity> {
        return entities.map { saveEntity(it) }
    }

    override fun save(request: Genre): Genre {
        val savedEntity = saveEntity(mapper.fromDomainToEntity(request))
        val mappedDomain = mapper.fromEntityToDomain(savedEntity)
        return mappedDomain
    }

    override fun saveAll(request: List<Genre>): List<Genre> {
        val entities = request.map { mapper.fromDomainToEntity(it) }
        val savedEntities = saveAllEntities(entities)
        val mappedDomains = savedEntities.map { mapper.fromEntityToDomain(it) }
        return mappedDomains
    }

    override fun updateEntity(entity: GenreEntity): Genre {
        val updatedEntity = repository.save(entity)
        val mappedDomain = mapper.fromEntityToDomain(updatedEntity)
        return mappedDomain
    }

    override fun update(id: Long, request: Genre): Genre {
        val entity = getEntityById(id)
        val mergedEntity = mapper.mergeEntityAndRequest(entity, request)
        val updatedEntity = updateEntity(mergedEntity)
        return updatedEntity
    }

    override fun deleteEntity(entity: GenreEntity): Boolean {
        repository.delete(entity)
        return true
    }

    override fun delete(id: Long): Boolean {
        val entity = getEntityById(id)
        return deleteEntity(entity)
    }

}