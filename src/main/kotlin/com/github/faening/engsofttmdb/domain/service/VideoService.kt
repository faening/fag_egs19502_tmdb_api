package com.github.faening.engsofttmdb.domain.service

import com.github.faening.engsofttmdb.data.model.db.VideoEntity
import com.github.faening.engsofttmdb.data.repository.VideoRepository
import com.github.faening.engsofttmdb.domain.contract.BaseService
import com.github.faening.engsofttmdb.domain.mapper.VideoMapper
import com.github.faening.engsofttmdb.domain.model.Video
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class VideoService<T, U, V> @Autowired constructor(
    private val repository: VideoRepository,
    private val mapper: VideoMapper
) : BaseService<VideoEntity, Video, Video> {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun getAllEntities(): List<VideoEntity> {
        return repository.findAll()
    }

    override fun getAll(): List<Video> {
        getAllEntities().let {
            return it.map { video -> mapper.fromEntityToDomain(video) }
        }
    }

    override fun getEntityById(id: Long): VideoEntity {
        return repository.findById(id).orElseThrow { throw RuntimeException("Video not found") }
    }

    override fun getById(id: Long): Video {
        getEntityById(id).let {
            return mapper.fromEntityToDomain(it)
        }
    }

    override fun saveEntity(entity: VideoEntity): VideoEntity {
//        if (entity.id != null && entityManager.contains(entity).not()) {
//            return repository.save(entityManager.merge(entity))
//        }
        return repository.save(entity)
    }

    override fun saveAllEntities(entities: List<VideoEntity>): List<VideoEntity> {
        return entities.map { saveEntity(it) }
    }

    override fun save(request: Video): Video {
        request.let {
            val savedEntity = saveEntity(mapper.fromDomainToEntity(it))
            return mapper.fromEntityToDomain(savedEntity)
        }
    }

    override fun saveAll(request: List<Video>): List<Video> {
        request.let {
            val entities = it.map { mapper.fromDomainToEntity(it) }
            val savedEntities = saveAllEntities(entities)
            return savedEntities.map { mapper.fromEntityToDomain(it) }
        }
    }

    override fun updateEntity(entity: VideoEntity): Video {
        entity.let {
            val savedEntity = saveEntity(it)
            return mapper.fromEntityToDomain(savedEntity)
        }
    }

    override fun update(id: Long, request: Video): Video {
        id.let {
            val entity = getEntityById(it)
            val updatedEntity = mapper.mergeEntityAndRequest(entity, request)
            val savedEntity = saveEntity(updatedEntity)
            return mapper.fromEntityToDomain(savedEntity)
        }
    }

    override fun deleteEntity(entity: VideoEntity): Boolean {
        entity.let {
            repository.delete(it)
            return true
        }
    }

    override fun delete(id: Long): Boolean {
        id.let {
            val entity = getEntityById(it)
            return deleteEntity(entity)
        }
    }

}