package com.github.faening.engsofttmdb.controller

import com.github.faening.engsofttmdb.data.model.api.*
import com.github.faening.engsofttmdb.domain.service.TmdbService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Suppress("unused")
@RestController
@RequestMapping("/tmdb")
class TmdbController @Autowired constructor(
    private val tmdbService: TmdbService
) {

    @GetMapping("/init")
    fun init() {
        tmdbService.initialize()
    }

    @GetMapping("/authenticate")
    fun authentication() : ResponseEntity<AuthenticationApiData> {
        return ResponseEntity.ok(tmdbService.authentication())
    }

    @GetMapping("/movies")
    fun getAllMovies(): ResponseEntity<List<MovieApiData>> {
        return ResponseEntity.ok(tmdbService.getAllMovies())
    }

    @GetMapping("/tv")
    fun getAllTvShows(): ResponseEntity<List<TvApiData>> {
        return ResponseEntity.ok(tmdbService.getAllTvShows())
    }

    @GetMapping("/genres")
    fun getAllGenres(): ResponseEntity<GenrePageApiData> {
        return ResponseEntity.ok(tmdbService.getAllGenres())
    }

    @GetMapping("/credits")
    fun getAllCredits(
        @RequestParam("movieId") movieId: Long
    ): ResponseEntity<CreditsApiData> {
        return ResponseEntity.ok(tmdbService.getMovieCredits(movieId))
    }

    @GetMapping("/reviews")
    fun getAllReviews(
        @RequestParam("movieId") movieId: Long
    ): ResponseEntity<List<ReviewApiData>> {
        val reviews = tmdbService.getMovieReviews(movieId)
        return ResponseEntity.ok(reviews)
    }

}