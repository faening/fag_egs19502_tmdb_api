package com.github.faening.engsofttmdb.domain.service

import com.github.faening.engsofttmdb.data.model.db.VideoEntity
import com.github.faening.engsofttmdb.data.repository.VideoRepository
import com.github.faening.engsofttmdb.domain.contract.BaseService
import com.github.faening.engsofttmdb.domain.mapper.VideoMapper
import com.github.faening.engsofttmdb.domain.model.Video
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class VideoService @Autowired constructor(
    private val repository: VideoRepository,
    private val mapper: VideoMapper
) : BaseService<VideoEntity, Video, Video> {

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
        return repository.save(entity)
    }

    override fun saveAllEntities(entities: List<VideoEntity>): List<VideoEntity> {
        return repository.saveAll(entities)
    }

    override fun save(request: Video): Video {
        val entity = mapper.fromDomainToEntity(request)
        return run {
            val savedEntity = saveEntity(entity)
            mapper.fromEntityToDomain(savedEntity)
        }
    }

    override fun saveAll(request: List<Video>): List<Video> {
        val entities = request.map { mapper.fromDomainToEntity(it) }
        return request.let {
            val savedEntities = saveAllEntities(entities)
            savedEntities.map { mapper.fromEntityToDomain(it) }
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
            val mergedEntity = mapper.mergeEntityAndRequest(entity, request)
            return updateEntity(mergedEntity)
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