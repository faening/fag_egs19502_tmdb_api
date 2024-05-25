package com.github.faening.engsofttmdb.controller

import com.github.faening.engsofttmdb.api.model.*
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

    @GetMapping("/authenticate")
    fun authentication() : ResponseEntity<AuthenticationData> {
        return ResponseEntity.ok(tmdbService.authentication())
    }

    @GetMapping("/movies")
    fun getAllMovies(): ResponseEntity<List<MovieData>> {
        return ResponseEntity.ok(tmdbService.getAllMovies())
    }

    @GetMapping("/tv")
    fun getAllTvShows(): ResponseEntity<List<TvData>> {
        return ResponseEntity.ok(tmdbService.getAllTvShows())
    }

    @GetMapping("/genres")
    fun getAllGenres(): ResponseEntity<GenrePageData> {
        return ResponseEntity.ok(tmdbService.getAllGenres())
    }

    @GetMapping("/credits")
    fun getAllCredits(
        @RequestParam("movieId") movieId: Int
    ): ResponseEntity<CreditsData> {
        return ResponseEntity.ok(tmdbService.getMovieCredits(movieId))
    }

    @GetMapping("/reviews")
    fun getAllReviews(
        @RequestParam("movieId") movieId: Int
    ): ResponseEntity<ReviewData> {
        return ResponseEntity.ok(tmdbService.getMovieReviews(movieId))
    }

}