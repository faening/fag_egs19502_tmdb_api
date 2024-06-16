package com.github.faening.engsofttmdb.domain.service

import com.github.faening.engsofttmdb.data.model.db.MovieEntity
import com.github.faening.engsofttmdb.data.repository.MovieRepository
import com.github.faening.engsofttmdb.domain.contract.BaseService
import com.github.faening.engsofttmdb.domain.mapper.*
import com.github.faening.engsofttmdb.domain.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MovieService @Autowired constructor(
    private val repository: MovieRepository,
    private val mapper: MovieMapper,
    private val castMapper: CastMapper,
    private val crewMapper: CrewMapper,
    private val reviewMapper: ReviewMapper,
    private val videoMapper: VideoMapper
) : BaseService<MovieEntity, Movie, Movie> {

    override fun getAllEntities(): List<MovieEntity> {
        return repository.findAll()
    }

    override fun getAll(): List<Movie> {
        val entities = repository.findAll()
        val moviesMapped = entities.map { movie -> mapper.fromEntityToDomain(movie) }
        return moviesMapped
    }

    override fun getEntityById(id: Long): MovieEntity {
        return repository.findById(id).orElseThrow { throw RuntimeException("Movie not found") }
    }

    override fun getById(id: Long): Movie {
        val entity = getEntityById(id)
        val movie = mapper.fromEntityToDomain(entity)
        return movie
    }

    override fun saveEntity(entity: MovieEntity): MovieEntity {
        return repository.save(entity)
    }

    override fun saveAllEntities(entities: List<MovieEntity>): List<MovieEntity> {
        return repository.saveAll(entities)
    }

    override fun save(request: Movie): Movie {
        val savedEntity = saveEntity(mapper.fromDomainToEntity(request))
        val mappedDomain = mapper.fromEntityToDomain(savedEntity)
        return mappedDomain
    }

    override fun saveAll(request: List<Movie>): List<Movie> {
        val entities = request.map { mapper.fromDomainToEntity(it) }
        val savedEntities = saveAllEntities(entities)
        val mappedDomains = savedEntities.map { mapper.fromEntityToDomain(it) }
        return mappedDomains
    }

    override fun updateEntity(entity: MovieEntity): Movie {
        val updatedEntity = repository.save(entity)
        val mappedDomain = mapper.fromEntityToDomain(updatedEntity)
        return mappedDomain
    }

    override fun update(id: Long, request: Movie): Movie {
        val entity = getEntityById(id)
        val mergedEntity = mapper.mergeEntityAndRequest(entity, request)
        val updatedEntity = updateEntity(mergedEntity)
        return updatedEntity
    }

    override fun deleteEntity(entity: MovieEntity): Boolean {
        repository.delete(entity)
        return true
    }

    override fun delete(id: Long): Boolean {
        val entity = getEntityById(id)
        return deleteEntity(entity)
    }

    fun getMovieCasts(movieId: Long): List<Cast> {
        val movie = getEntityById(movieId)
        val casts = movie.casts?.map { cast -> castMapper.fromEntityToDomain(cast) }
        return casts ?: emptyList()
    }

    fun getMovieCrews(movieId: Long): List<Crew> {
        val movie = getEntityById(movieId)
        val crews = movie.crews?.map { crew -> crewMapper.fromEntityToDomain(crew) }
        return crews ?: emptyList()
    }

    fun getMovieReviews(movieId: Long): List<Review> {
        val movie = getEntityById(movieId)
        val reviews = movie.reviews?.map { review -> reviewMapper.fromEntityToDomain(review) }
        return reviews ?: emptyList()
    }

    fun getMovieVideos(movieId: Long): List<Video> {
        val movie = getEntityById(movieId)
        val videos = movie.videos?.map { video -> videoMapper.fromEntityToDomain(video) }
        return videos ?: emptyList()
    }

}