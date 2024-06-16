package com.github.faening.engsofttmdb.domain.service

import com.github.faening.engsofttmdb.data.model.db.CrewEntity
import com.github.faening.engsofttmdb.data.repository.CrewRepository
import com.github.faening.engsofttmdb.domain.contract.BaseService
import com.github.faening.engsofttmdb.domain.mapper.CrewMapper
import com.github.faening.engsofttmdb.domain.model.Crew
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CrewService @Autowired constructor(
    private val repository: CrewRepository,
    private val mapper: CrewMapper
) : BaseService<CrewEntity, Crew, Crew> {

    override fun getAllEntities(): List<CrewEntity> {
        return repository.findAll()
    }

    override fun getAll(): List<Crew> {
        getAllEntities().let {
            return it.map { crew -> mapper.fromEntityToDomain(crew) }
        }
    }

    override fun getEntityById(id: Long): CrewEntity {
        return repository.findById(id).orElseThrow { throw RuntimeException("Crew not found") }
    }

    override fun getById(id: Long): Crew {
        getEntityById(id).let {
            return mapper.fromEntityToDomain(it)
        }
    }

    override fun saveEntity(entity: CrewEntity): CrewEntity {
        return repository.save(entity)
    }

    override fun saveAllEntities(entities: List<CrewEntity>): List<CrewEntity> {
        return repository.saveAll(entities)
    }

    override fun save(request: Crew): Crew? {
        val entity = saveEntity(mapper.fromDomainToEntity(request))
        return run {
            val savedEntity = saveEntity(entity)
            mapper.fromEntityToDomain(savedEntity)
        }
    }

    override fun saveAll(request: List<Crew>): List<Crew> {
        val entities = request.map { mapper.fromDomainToEntity(it) }
        return request.let {
            val savedEntities = saveAllEntities(entities)
            savedEntities.map { mapper.fromEntityToDomain(it) }
        }
    }

    override fun updateEntity(entity: CrewEntity): Crew {
        entity.let {
            val updatedEntity = repository.save(entity)
            return mapper.fromEntityToDomain(updatedEntity)
        }
    }

    override fun update(id: Long, request: Crew): Crew {
        id.let {
            val entity = getEntityById(id)
            val mergedEntity = mapper.mergeEntityAndRequest(entity, request)
            val updatedEntity = updateEntity(mergedEntity)
            return updatedEntity
        }
    }

    override fun deleteEntity(entity: CrewEntity): Boolean {
        repository.delete(entity)
        return true
    }

    override fun delete(id: Long): Boolean {
        id.let {
            val entity = getEntityById(id)
            return deleteEntity(entity)
        }
    }

}