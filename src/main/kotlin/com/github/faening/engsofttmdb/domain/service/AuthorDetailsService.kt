package com.github.faening.engsofttmdb.domain.service

import com.github.faening.engsofttmdb.data.model.db.AuthorDetailsEntity
import com.github.faening.engsofttmdb.data.repository.AuthorDetailsRepository
import com.github.faening.engsofttmdb.domain.contract.BaseService
import com.github.faening.engsofttmdb.domain.mapper.AuthorDetailsMapper
import com.github.faening.engsofttmdb.domain.model.AuthorDetails
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthorDetailsService @Autowired constructor(
    private val repository: AuthorDetailsRepository,
    private val mapper: AuthorDetailsMapper
) : BaseService<AuthorDetailsEntity, AuthorDetails, AuthorDetails> {

    override fun getAllEntities(): List<AuthorDetailsEntity> {
        return repository.findAll()
    }

    override fun getAll(): List<AuthorDetails> {
        getAllEntities().let {
            return it.map { authorDetails -> mapper.fromEntityToDomain(authorDetails) }
        }
    }

    override fun getEntityById(id: Long): AuthorDetailsEntity {
        repository.findById(id).orElseThrow { throw Exception("AuthorDetails not found") }.let {
            return it
        }
    }

    override fun getById(id: Long): AuthorDetails {
        getEntityById(id).let {
            return mapper.fromEntityToDomain(it)
        }
    }

    override fun saveEntity(entity: AuthorDetailsEntity): AuthorDetailsEntity {
        return repository.save(entity)
    }

    override fun saveAllEntities(entities: List<AuthorDetailsEntity>): List<AuthorDetailsEntity> {
        return repository.saveAll(entities)
    }

    override fun save(request: AuthorDetails): AuthorDetails? {
        val entity = mapper.fromDomainToEntity(request)
        return run {
            val savedEntity = saveEntity(entity)
            mapper.fromEntityToDomain(savedEntity)
        }
    }

    override fun saveAll(request: List<AuthorDetails>): List<AuthorDetails>? {
        val entities = request.map { mapper.fromDomainToEntity(it) }
        return request.let {
            val savedEntities = saveAllEntities(entities)
            savedEntities.map { mapper.fromEntityToDomain(it) }
        }
    }

    override fun updateEntity(entity: AuthorDetailsEntity): AuthorDetails {
        entity.let {
            val updatedEntity = repository.save(entity)
            return mapper.fromEntityToDomain(updatedEntity)
        }
    }

    override fun update(id: Long, request: AuthorDetails): AuthorDetails {
        id.let {
            val entity = getEntityById(id)
            val mergedEntity = mapper.mergeEntityAndRequest(entity, request)
            val updatedEntity = updateEntity(mergedEntity)
            return updatedEntity
        }
    }

    override fun deleteEntity(entity: AuthorDetailsEntity): Boolean {
        entity.let {
            repository.delete(it)
            return true

        }
    }

    override fun delete(id: Long): Boolean {
        val entity = getEntityById(id)
        id.let {
            return deleteEntity(entity)
        }
    }

    fun findByNameOrUsernameIgnoreCase(name: String, username: String): AuthorDetailsEntity? {
        return repository.findByNameOrUsernameIgnoreCase(name, username)
    }

}