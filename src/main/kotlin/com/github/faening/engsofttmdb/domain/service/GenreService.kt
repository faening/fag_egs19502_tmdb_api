package com.github.faening.engsofttmdb.domain.service

import com.github.faening.engsofttmdb.data.model.db.GenreEntity
import com.github.faening.engsofttmdb.data.repository.GenreRepository
import com.github.faening.engsofttmdb.domain.contract.BaseService
import com.github.faening.engsofttmdb.domain.mapper.GenreMapper
import com.github.faening.engsofttmdb.domain.model.Genre
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class  GenreService @Autowired constructor(
    private val repository: GenreRepository,
    private val mapper: GenreMapper
) : BaseService<GenreEntity, Genre, Genre> {

    override fun getAllEntities(): List<GenreEntity> {
        return repository.findAll()
    }

    @Transactional
    override fun getAll(): List<Genre> {
        getAllEntities().let {
            return it.map { genre -> mapper.fromEntityToDomain(genre) }
        }
    }

    override fun getEntityById(id: Long): GenreEntity {
        return repository.findById(id).orElseThrow { throw RuntimeException("Genre not found") }
    }

    @Transactional
    override fun getById(id: Long): Genre {
        getEntityById(id).let {
            return mapper.fromEntityToDomain(it)
        }
    }

    override fun saveEntity(entity: GenreEntity): GenreEntity {
        return repository.save(entity)
    }

    override fun saveAllEntities(entities: List<GenreEntity>): List<GenreEntity> {
        return repository.saveAll(entities)
    }

    override fun save(request: Genre): Genre {
        val entity = mapper.fromDomainToEntity(request)
        return run {
            val savedEntity = saveEntity(entity)
            mapper.fromEntityToDomain(savedEntity)
        }
    }

    override fun saveAll(request: List<Genre>): List<Genre> {
        val entities = request.map { mapper.fromDomainToEntity(it) }
        return  entities.let {
            val savedEntities = saveAllEntities(entities)
            savedEntities.map { mapper.fromEntityToDomain(it) }
        }
    }

    override fun updateEntity(entity: GenreEntity): Genre {
        entity.let {
            val updatedEntity = repository.save(entity)
            return mapper.fromEntityToDomain(updatedEntity)
        }
    }

    override fun update(id: Long, request: Genre): Genre {
        id.let {
            val entity = getEntityById(it)
            val mergedEntity = mapper.mergeEntityAndRequest(entity, request)
            return updateEntity(mergedEntity)
        }
    }

    override fun deleteEntity(entity: GenreEntity): Boolean {
        entity.let {
            repository.delete(entity)
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