package com.github.faening.engsofttmdb.domain.service

import com.github.faening.engsofttmdb.data.repository.MovieRepository
import com.github.faening.engsofttmdb.domain.mapper.MovieMapper
import com.github.faening.engsofttmdb.domain.model.Movie
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Suppress("unused")
@Service
class MovieService @Autowired constructor(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper
) : BaseService<Movie, Movie>() {

    override fun getAll(): List<Movie> {
        val movies = movieRepository.findAll()
        return movies.map { movieMapper.fromEntityToDomain(it) }
    }

    override fun getById(id: Long): Movie {
        return movieMapper.fromEntityToDomain(
            movieRepository.findById(id).orElseThrow { throw Exception("Movie not found") }
        )
    }

    override fun save(request: Movie): Movie {
        return movieMapper.fromEntityToDomain(
            movieRepository.save(movieMapper.fromDomainToEntity(request))
        )
    }

    override fun saveAll(request: List<Movie>): List<Movie> {
        return movieRepository.saveAll(
            request.map { movieMapper.fromDomainToEntity(it) }
        ).map { movieMapper.fromEntityToDomain(it) }
    }

    override fun update(id: Long, request: Movie): Movie {
        return movieMapper.fromEntityToDomain(
            movieRepository.save(movieMapper.fromDomainToEntity(request))
        )
    }

    override fun delete(id: Long) {
        movieRepository.deleteById(id)
    }

}