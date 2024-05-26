package com.github.faening.engsofttmdb.domain.service

import com.github.faening.engsofttmdb.data.repository.MovieRepository
import com.github.faening.engsofttmdb.domain.model.Movie
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MovieService @Autowired constructor(
    private val movieRepository: MovieRepository
) : AbstractService<Movie, Movie>() {

    override fun getAll(): List<Movie> {
        TODO("Not yet implemented")
    }

    override fun getById(id: Int): Movie {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int) {
        TODO("Not yet implemented")
    }

    override fun update(id: Int, request: Movie): Movie {
        TODO("Not yet implemented")
    }

    override fun create(request: Movie): Movie {
        TODO("Not yet implemented")
    }

}