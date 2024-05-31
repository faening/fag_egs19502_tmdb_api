package com.github.faening.engsofttmdb.domain.service

import com.github.faening.engsofttmdb.data.repository.GenreRepository
import com.github.faening.engsofttmdb.domain.mapper.GenreMapper
import com.github.faening.engsofttmdb.domain.model.Genre
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Suppress("unused")
@Service
class GenreService @Autowired constructor(
    private val genreRepository: GenreRepository,
    private val genreMapper: GenreMapper
) : BaseService<Genre, Genre>() {

    @Transactional
    override fun getAll(): List<Genre> {
        val genres = genreRepository.findAll()
        return genres.map { genreMapper.fromEntityToDomain(it) }
    }

    @Transactional
    override fun getById(id: Long): Genre {
        return genreMapper.fromEntityToDomain(
            genreRepository.findById(id).orElseThrow { throw Exception("Genre not found") }
        )
    }

    override fun save(request: Genre): Genre {
        return genreMapper.fromEntityToDomain(
            genreRepository.save(genreMapper.fromDomainToEntity(request))
        )
    }

    override fun saveAll(request: List<Genre>): List<Genre> {
        return genreRepository.saveAll(
            request.map { genreMapper.fromDomainToEntity(it) }
        ).map { genreMapper.fromEntityToDomain(it) }
    }

    override fun update(id: Long, request: Genre): Genre {
        return genreMapper.fromEntityToDomain(
            genreRepository.save(genreMapper.fromDomainToEntity(request))
        )
    }

    override fun delete(id: Long) {
        genreRepository.deleteById(id)
    }

}