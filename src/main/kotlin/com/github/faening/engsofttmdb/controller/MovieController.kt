package com.github.faening.engsofttmdb.controller

import com.github.faening.engsofttmdb.data.model.db.MovieEntity
import com.github.faening.engsofttmdb.domain.model.*
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
    private val service: MovieService
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

    @GetMapping("/{movieId}/videos")
    fun getMovieVideos(@PathVariable movieId: Long): ResponseEntity<List<Video>> {
        val videos = service.getMovieVideos(movieId)
        return ResponseEntity.ok(videos)
    }

}