package com.github.faening.engsofttmdb.controller

import com.github.faening.engsofttmdb.data.model.*
import com.github.faening.engsofttmdb.domain.service.MovieService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Suppress("unused")
@RestController
@RequestMapping("/tmdb")
class MovieController @Autowired constructor(
    private val movieService: MovieService
) {

    @GetMapping("/authenticate")
    fun authentication() : ResponseEntity<AuthenticationData> {
        return ResponseEntity.ok(movieService.authentication())
    }

    @GetMapping("/movies")
    fun getAllMovies(): ResponseEntity<List<MovieData>> {
        return ResponseEntity.ok(movieService.getAllMovies())
    }

    @GetMapping("/tv")
    fun getAllTvShows(): ResponseEntity<List<TvData>> {
        return ResponseEntity.ok(movieService.getAllTvShows())
    }

    @GetMapping("/genres")
    fun getAllGenres(): ResponseEntity<GenrePageData> {
        return ResponseEntity.ok(movieService.getAllGenres())
    }

    @GetMapping("/credits")
    fun getAllCredits(
        @RequestParam("movieId") movieId: Int
    ): ResponseEntity<CreditsData> {
        return ResponseEntity.ok(movieService.getMovieCredits(movieId))
    }

    @GetMapping("/reviews")
    fun getAllReviews(
        @RequestParam("movieId") movieId: Int
    ): ResponseEntity<ReviewData> {
        return ResponseEntity.ok(movieService.getMovieReviews(movieId))
    }

}