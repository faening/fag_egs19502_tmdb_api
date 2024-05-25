package com.github.faening.engsofttmdb.domain.service

import com.github.faening.engsofttmdb.api.model.*
import com.github.faening.engsofttmdb.api.service.TmdbService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TmdbService @Autowired constructor(
    private val tmdbService: TmdbService
) {

    /**
     * Este método realiza a verificação da autenticação com a API do TMDB.
     *
     * @return Retorna um objeto do tipo AuthenticationData.
     */
    fun authentication(): AuthenticationData? {
        val call = tmdbService.authentication()
        return try {
            val response = call.execute()
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * Este método realiza a busca de todos os filmes disponíveis na API do TMDB.
     *
     * @return Retorna uma lista de objetos do tipo MovieData.
     */
    fun getAllMovies(): List<MovieData> {
        var movieData = emptyList<MovieData>()
        val call = tmdbService.getAllMovies()

        try {
            val response = call.execute()
            if (response.isSuccessful) {
                movieData = response.body()?.results ?: emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return movieData
    }

    /**
     * Este método realiza a busca de todas as séries de TV disponíveis na API do TMDB.
     *
     * @return Retorna uma lista de objetos do tipo TvData.
     */
    fun getAllTvShows(): List<TvData> {
        var tvData = emptyList<TvData>()
        val call = tmdbService.getAllTvShows()

        try {
            val response = call.execute()
            if (response.isSuccessful) {
                tvData = response.body()?.results ?: emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return tvData
    }

    /**
     * Este método realiza a busca de todos os gêneros disponíveis na API do TMDB.
     *
     * @return Retorna um objeto do tipo GenrePageData.
     */
    fun getAllGenres(): GenrePageData {
        var genreData = GenrePageData(emptyList())
        val call = tmdbService.getAllGenres()

        try {
            val response = call.execute()
            if (response.isSuccessful) {
                genreData = (response.body() ?: GenrePageData(emptyList()))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return genreData
    }

    /**
     * Este método realiza a busca dos créditos de um filme específico na API do TMDB.
     *
     * @param movieId ID do filme.
     * @return Retorna um objeto do tipo CreditsData.
     */
    fun getMovieCredits(movieId: Int): CreditsData {
        var creditsData = CreditsData(id = 0, cast = emptyList(), crew = emptyList())
        val call = tmdbService.getMovieCredits(movieId)

        try {
            val response = call.execute()
            if (response.isSuccessful) {
                creditsData = response.body() ?: creditsData
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return creditsData
    }

    /**
     * Este método realiza a busca das avaliações de um filme específico na API do TMDB.
     *
     * @param movieId ID do filme.
     * @return Retorna um objeto do tipo ReviewData.
     */
    fun getMovieReviews(movieId: Int): ReviewData {
        var reviewData = ReviewData(
            author = "", authorDetails = AuthorData(
                name = "",
                username = "",
                avatarPath = "",
                rating = 0
            ), content = "", id = "", url = "", createdAt = "", updatedAt = ""
        )
        val call = tmdbService.getMovieReviews(movieId)

        try {
            val response = call.execute()
            if (response.isSuccessful) {
                reviewData = response.body()?.results?.get(0) ?: reviewData
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return reviewData
    }

}