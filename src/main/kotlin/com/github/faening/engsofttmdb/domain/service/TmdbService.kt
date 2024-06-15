package com.github.faening.engsofttmdb.domain.service

import com.github.faening.engsofttmdb.data.api.TmdbApi
import com.github.faening.engsofttmdb.data.model.api.*
import com.github.faening.engsofttmdb.data.model.db.*
import com.github.faening.engsofttmdb.domain.mapper.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Suppress("unused")
@Service
class TmdbService @Autowired constructor(
    private val tmdbApi: TmdbApi,
    private val genreService: GenreService,
    private val genreMapper: GenreMapper,
    private val movieService: MovieService<Any?, Any?, Any?>,
    private val movieMapper: MovieMapper,
    private val castService: CastService,
    private val castMapper: CastMapper,
    private val crewService: CrewService,
    private val crewMapper: CrewMapper,
    private val authorDetailsService: AuthorDetailsService,
    private val authorDetailsMapper: AuthorDetailsMapper,
    private val reviewService: ReviewService,
    private val reviewMapper: ReviewMapper
) {

    fun initialize() {
        fetchGenresAndSaveInLocalDatabase()
        fetchMoviesAndSaveInLocalDatabase()
    }

    private fun fetchGenresAndSaveInLocalDatabase() {
        val apiGenres: List<GenreApiData> = getAllGenres().genres
        val genresMappedToEntity: List<GenreEntity> = apiGenres.map { genre -> genreMapper.fromApiDataToEntity(genre) }
        genreService.saveAllEntities(genresMappedToEntity)
    }

    private fun fetchMoviesAndSaveInLocalDatabase() {
        val apiMovies: List<MovieApiData> = getAllMovies()
        val moviesMappedToEntity: List<MovieEntity> = apiMovies.map { movie -> movieMapper.fromApiDataToEntity(movie) }
        val savedMovies = movieService.saveAllEntities(moviesMappedToEntity)

        savedMovies.forEach { movie ->
            val updatedMovie = movie.copy(
                casts = fetchAndSaveCasts(movie),
                crews = fetchAndSaveCrews(movie),
                reviews = fetchAndSaveReviews(movie)
            )
            movieService.saveEntity(updatedMovie)
        }
    }

    private fun fetchAndSaveCasts(movie: MovieEntity): MutableSet<CastEntity> {
        val casts = getMovieCredits(movie.tmdbId!!).cast.map { castMapper.fromApiDataToEntity(it) }
        return castService.saveAllEntities(casts).toMutableSet()
    }

    private fun fetchAndSaveCrews(movie: MovieEntity): MutableSet<CrewEntity> {
        val crews = getMovieCredits(movie.tmdbId!!).crew.map { crewMapper.fromApiDataToEntity(it) }
        return crewService.saveAllEntities(crews).toMutableSet()
    }

    private fun fetchAndSaveReviews(movie: MovieEntity): MutableList<ReviewEntity> {
        val reviews = getMovieReviews(movie.tmdbId!!)
        return reviews.map { review ->
            val authorDetails: AuthorDetailsEntity = authorDetailsMapper.fromApiDataToEntity(review.authorDetails!!)
            val savedAuthorDetails = saveAuthorDetails(authorDetails)

            val reviewEntity: ReviewEntity = reviewMapper.fromApiDataToEntity(review)
            reviewEntity.authorDetails = savedAuthorDetails
            reviewEntity.movie = movie
            reviewService.saveEntity(reviewEntity)
        }.toMutableList()
    }

    private fun saveAuthorDetails(authorDetails: AuthorDetailsEntity) : AuthorDetailsEntity {
        val existingAuthorDetails: AuthorDetailsEntity? = authorDetailsService.findByNameOrUsernameIgnoreCase(authorDetails.name, authorDetails.username)
        return existingAuthorDetails ?: authorDetailsService.saveEntity(authorDetails)
    }

    /**
     * Este método realiza a verificação da autenticação com a API do TMDB.
     *
     * @return Retorna um objeto do tipo AuthenticationData.
     */
    fun authentication(): AuthenticationApiData? {
        val call = tmdbApi.authentication()
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
     * Este método realiza a busca de todos os gêneros disponíveis na API do TMDB.
     *
     * @return Retorna um objeto do tipo GenrePageApiData.
     */
    fun getAllGenres(): GenrePageApiData {
        var genreData = GenrePageApiData(emptyList())
        val call = tmdbApi.getAllGenres()

        try {
            val response = call.execute()
            if (response.isSuccessful) {
                genreData = (response.body() ?: GenrePageApiData(emptyList()))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return genreData
    }

    /**
     * Este método realiza a busca de 20 filmes lançados recentemente, disponíveis na API do TMDB.
     *
     * @return Retorna uma lista de objetos do tipo MovieData.
     */
    fun getAllMovies(): List<MovieApiData> {
        var movieApiData = emptyList<MovieApiData>()
        val call = tmdbApi.getAllMovies()

        try {
            val response = call.execute()
            if (response.isSuccessful) {
                movieApiData = response.body()?.results ?: emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return movieApiData
    }

    /**
     * Este método realiza a busca dos créditos de um filme específico na API do TMDB.
     *
     * @param tmdbId ID do filme.
     * @return Retorna um objeto do tipo CreditsData.
     */
    fun getMovieCredits(tmdbId: Long): CreditsApiData {
        var creditsApiData = CreditsApiData(movieId = 0, cast = emptyList(), crew = emptyList())
        val call = tmdbApi.getMovieCredits(tmdbId)

        try {
            val response = call.execute()
            if (response.isSuccessful) {
                creditsApiData = response.body() ?: creditsApiData
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return creditsApiData
    }

    /**
     * Este método realiza a busca de todas as séries de TV disponíveis na API do TMDB.
     *
     * @return Retorna uma lista de objetos do tipo TvData.
     */
    fun getAllTvShows(): List<TvApiData> {
        var tvApiData = emptyList<TvApiData>()
        val call = tmdbApi.getAllTvShows()

        try {
            val response = call.execute()
            if (response.isSuccessful) {
                tvApiData = response.body()?.results ?: emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return tvApiData
    }

    /**
     * Este método realiza a busca das avaliações de um filme específico na API do TMDB.
     *
     * @param movieId ID do filme.
     * @return Retorna um objeto do tipo ReviewData.
     */
    fun getMovieReviews(movieId: Long): List<ReviewApiData> {
        var reviewApiData = emptyList<ReviewApiData>()
        val call = tmdbApi.getMovieReviews(movieId)

        try {
            val response = call.execute()
            if (response.isSuccessful) {
                reviewApiData = response.body()?.results ?: emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return reviewApiData
    }

}