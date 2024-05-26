package com.github.faening.engsofttmdb.data.service

import com.github.faening.engsofttmdb.data.model.api.*
import org.springframework.stereotype.Service
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

@Service
interface TmdbService {

    @GET("authentication")
    fun authentication(): Call<AuthenticationData>

    @GET("discover/movie")
    fun getAllMovies(): Call<ResponsePageData<MovieData>>

    @GET("discover/tv")
    fun getAllTvShows(): Call<ResponsePageData<TvData>>

    @GET("genre/movie/list")
    fun getAllGenres(): Call<GenrePageData>

    @GET("movie/{movie_id}/credits")
    fun getMovieCredits(@Path("movie_id") movieId: Int): Call<CreditsData>

    @GET("movie/{movie_id}/reviews")
    fun getMovieReviews(@Path("movie_id") movieId: Int): Call<ResponsePageData<ReviewData>>

}