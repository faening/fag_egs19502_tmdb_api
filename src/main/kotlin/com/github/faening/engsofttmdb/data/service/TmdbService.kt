package com.github.faening.engsofttmdb.data.service

import com.github.faening.engsofttmdb.data.model.api.*
import org.springframework.stereotype.Service
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

@Service
interface TmdbService {

    @GET("authentication")
    fun authentication(): Call<AuthenticationApiData>

    @GET("discover/movie")
    fun getAllMovies(): Call<ResponsePageApiData<MovieApiData>>

    @GET("discover/tv")
    fun getAllTvShows(): Call<ResponsePageApiData<TvApiData>>

    @GET("genre/movie/list")
    fun getAllGenres(): Call<GenrePageData>

    @GET("movie/{movie_id}/credits")
    fun getMovieCredits(@Path("movie_id") movieId: Int): Call<CreditsData>

    @GET("movie/{movie_id}/reviews")
    fun getMovieReviews(@Path("movie_id") movieId: Int): Call<ResponsePageApiData<ReviewData>>

}