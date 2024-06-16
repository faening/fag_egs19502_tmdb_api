package com.github.faening.engsofttmdb.domain.service

import com.github.faening.engsofttmdb.data.model.db.CastEntity
import com.github.faening.engsofttmdb.data.repository.CastRepository
import com.github.faening.engsofttmdb.domain.contract.BaseService
import com.github.faening.engsofttmdb.domain.mapper.CastMapper
import com.github.faening.engsofttmdb.domain.model.Cast
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CastService @Autowired constructor(
    private val repository: CastRepository,
    private val mapper: CastMapper
) : BaseService<CastEntity, Cast, Cast> {

    override fun getAllEntities(): List<CastEntity> {
        return repository.findAll()
    }

    override fun getAll(): List<Cast> {
        val entities = repository.findAll()
        val castsMapped = entities.map { cast -> mapper.fromEntityToDomain(cast) }
        return castsMapped
    }

    override fun getEntityById(id: Long): CastEntity {
        return repository.findById(id).orElseThrow { throw RuntimeException("Cast not found") }
    }

    override fun getById(id: Long): Cast {
        val entity = getEntityById(id)
        val cast = mapper.fromEntityToDomain(entity)
        return cast
    }

    override fun saveEntity(entity: CastEntity): CastEntity {
        return repository.save(entity)
    }

    override fun saveAllEntities(entities: List<CastEntity>): List<CastEntity> {
        return entities.map { saveEntity(it) }
    }

    override fun save(request: Cast): Cast? {
        val entity = mapper.fromDomainToEntity(request)
        return run {
            val savedEntity = saveEntity(entity)
            mapper.fromEntityToDomain(savedEntity)
        }
    }

    override fun saveAll(request: List<Cast>): List<Cast> {
        val entities = request.map { mapper.fromDomainToEntity(it) }
        val savedEntities = saveAllEntities(entities)
        val mappedDomains = savedEntities.map { mapper.fromEntityToDomain(it) }
        return mappedDomains
    }

    override fun updateEntity(entity: CastEntity): Cast {
        val updatedEntity = repository.save(entity)
        val mappedDomain = mapper.fromEntityToDomain(updatedEntity)
        return mappedDomain
    }

    override fun update(id: Long, request: Cast): Cast {
        val entity = getEntityById(id)
        val mergedEntity = mapper.mergeEntityAndRequest(entity, request)
        val updatedEntity = updateEntity(mergedEntity)
        return updatedEntity
    }

    override fun deleteEntity(entity: CastEntity): Boolean {
        repository.delete(entity)
        return true
    }

    override fun delete(id: Long): Boolean {
        val entity = getEntityById(id)
        return deleteEntity(entity)
    }

}