package com.github.faening.engsofttmdb.domain.service

import com.github.faening.engsofttmdb.data.model.db.CrewEntity
import com.github.faening.engsofttmdb.data.repository.CrewRepository
import com.github.faening.engsofttmdb.domain.contract.BaseService
import com.github.faening.engsofttmdb.domain.mapper.CrewMapper
import com.github.faening.engsofttmdb.domain.model.Crew
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CrewService @Autowired constructor(
    private val repository: CrewRepository,
    private val mapper: CrewMapper
) : BaseService<CrewEntity, Crew, Crew> {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun getAllEntities(): List<CrewEntity> {
        return repository.findAll()
    }

    override fun getAll(): List<Crew> {
        val entities = repository.findAll()
        val crewsMapped = entities.map { crew -> mapper.fromEntityToDomain(crew) }
        return crewsMapped
    }

    override fun getEntityById(id: Long): CrewEntity {
        return repository.findById(id).orElseThrow { throw RuntimeException("Crew not found") }
    }

    override fun getById(id: Long): Crew {
        val entity = getEntityById(id)
        val crew = mapper.fromEntityToDomain(entity)
        return crew
    }

    override fun saveEntity(entity: CrewEntity): CrewEntity {
        if (entity.id != null && entityManager.contains(entity).not()) {
            return repository.save(entityManager.merge(entity))
        }
        return repository.save(entity)
    }

    override fun saveAllEntities(entities: List<CrewEntity>): List<CrewEntity> {
        return entities.map { saveEntity(it) }
    }

    override fun save(request: Crew): Crew {
        val savedEntity = saveEntity(mapper.fromDomainToEntity(request))
        val mappedDomain = mapper.fromEntityToDomain(savedEntity)
        return mappedDomain
    }

    override fun saveAll(request: List<Crew>): List<Crew> {
        val entities = request.map { mapper.fromDomainToEntity(it) }
        val savedEntities = saveAllEntities(entities)
        val mappedDomains = savedEntities.map { mapper.fromEntityToDomain(it) }
        return mappedDomains
    }

    override fun updateEntity(entity: CrewEntity): Crew {
        val updatedEntity = repository.save(entity)
        val mappedDomain = mapper.fromEntityToDomain(updatedEntity)
        return mappedDomain
    }

    override fun update(id: Long, request: Crew): Crew {
        val entity = getEntityById(id)
        val mergedEntity = mapper.mergeEntityAndRequest(entity, request)
        val updatedEntity = updateEntity(mergedEntity)
        return updatedEntity
    }

    override fun deleteEntity(entity: CrewEntity): Boolean {
        repository.delete(entity)
        return true
    }

    override fun delete(id: Long): Boolean {
        val entity = getEntityById(id)
        return deleteEntity(entity)
    }

    fun findByTmdbId(tmdbId: Long): CrewEntity? {
        val entity = repository.findByTmdbId(tmdbId)
        return entity
    }

}