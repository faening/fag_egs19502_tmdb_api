package com.github.faening.engsofttmdb.controller

import com.github.faening.engsofttmdb.data.model.db.MovieEntity
import com.github.faening.engsofttmdb.domain.model.Cast
import com.github.faening.engsofttmdb.domain.model.Crew
import com.github.faening.engsofttmdb.domain.model.Movie
import com.github.faening.engsofttmdb.domain.model.Review
import com.github.faening.engsofttmdb.domain.service.MovieService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Suppress("unused")
@RestController
@RequestMapping("/movies")
class MovieController @Autowired constructor(
    private val service: MovieService<MovieEntity, Movie, Movie>
) : BaseController<MovieEntity, Movie, Movie>(service) {

    @GetMapping("/{movieId}/casts")
    fun getMovieCasts(@PathVariable movieId: Long): ResponseEntity<List<Cast>> {
        val casts = service.getMovieCasts(movieId)
        return ResponseEntity.ok(casts)
    }

    @GetMapping("/{movieId}/crews")
    fun getMovieCrews(@PathVariable movieId: Long): ResponseEntity<List<Crew>> {
        val crews = service.getMovieCrews(movieId)
        return ResponseEntity.ok(crews)
    }

    @GetMapping("/{movieId}/reviews")
    fun getMovieReviews(@PathVariable movieId: Long): ResponseEntity<List<Review>> {
        val reviews = service.getMovieReviews(movieId)
        return ResponseEntity.ok(reviews)
    }

}