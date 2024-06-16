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
        getAllEntities().let {
            return it.map { cast -> mapper.fromEntityToDomain(cast) }
        }
    }

    override fun getEntityById(id: Long): CastEntity {
        return repository.findById(id).orElseThrow { throw RuntimeException("Cast not found") }
    }

    override fun getById(id: Long): Cast {
        getEntityById(id).let {
            return mapper.fromEntityToDomain(it)
        }
    }

    override fun saveEntity(entity: CastEntity): CastEntity {
        return repository.save(entity)
    }

    override fun saveAllEntities(entities: List<CastEntity>): List<CastEntity> {
        return repository.saveAll(entities)
    }

    override fun save(request: Cast): Cast? {
        val entity = mapper.fromDomainToEntity(request)
        return run {
            val savedEntity = saveEntity(entity)
            mapper.fromEntityToDomain(savedEntity)
        }
    }

    override fun saveAll(request: List<Cast>): List<Cast>? {
        val entities = request.map { mapper.fromDomainToEntity(it) }
        return request.let {
            val savedEntities = saveAllEntities(entities)
            savedEntities.map { mapper.fromEntityToDomain(it) }
        }
    }

    override fun updateEntity(entity: CastEntity): Cast {
        entity.let {
            val updatedEntity = repository.save(entity)
            return mapper.fromEntityToDomain(updatedEntity)
        }
    }

    override fun update(id: Long, request: Cast): Cast {
        id.let {
            val entity = getEntityById(it)
            val mergedEntity = mapper.mergeEntityAndRequest(entity, request)
            return updateEntity(mergedEntity)
        }
    }

    override fun deleteEntity(entity: CastEntity): Boolean {
        entity.let {
            repository.delete(it)
            return true
        }
    }

    override fun delete(id: Long): Boolean {
        id.let {
            val entity = getEntityById(id)
            return deleteEntity(entity)
        }
    }

}