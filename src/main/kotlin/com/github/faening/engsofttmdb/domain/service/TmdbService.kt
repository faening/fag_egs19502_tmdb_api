package com.github.faening.engsofttmdb.domain.service

import com.github.faening.engsofttmdb.data.model.api.*
import com.github.faening.engsofttmdb.data.model.db.GenreEntity
import com.github.faening.engsofttmdb.data.model.db.MovieEntity
import com.github.faening.engsofttmdb.data.service.TmdbService
import com.github.faening.engsofttmdb.domain.mapper.GenreMapper
import com.github.faening.engsofttmdb.domain.mapper.MovieMapper
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Suppress("unused")
@Service
class TmdbService @Autowired constructor(
    private val tmdbService: TmdbService,
    private val genreMapper: GenreMapper,
    private val genreService: GenreService,
    private val movieMapper: MovieMapper,
    private val movieService: MovieService,
) {

    @PostConstruct
    private fun initialize() {
        initializeGenres()
        initializeMovies()
    }

    /**
     * Este método realiza a inicialização dos gêneros no banco de dados.
     * Os gêneros são buscados da API do TMDB, mapeados para entidades e salvos no banco de dados.
     */
    private fun initializeGenres() {
        // Buscar os gêneros da API do TMDB
        val apiGenres: List<GenreApiData> = getAllGenres().genres
        // Mapear os gêneros da API para entidades
        val apiGenresMappedToEntity: List<GenreEntity> = apiGenres.map { genre -> genreMapper.fromApiDataToEntity(genre) }
        // Salvar os gêneros no banco de dados
        genreService.saveAll(apiGenresMappedToEntity.map { genreMapper.fromEntityToDomain(it) })
    }

    /**
     * Este método realiza a inicialização dos filmes no banco de dados.
     * Os filmes são buscados da API do TMDB, mapeados para entidades e salvos no banco de dados.
     */
    private fun initializeMovies() {
        // Buscar os filmes da API do TMDB
        val apiMovies: List<MovieApiData> = getAllMovies()
        // Mapear os filmes da API para entidades
        val apiMoviesMappedToEntity: List<MovieEntity> = apiMovies.map { movie -> movieMapper.fromApiDataToEntity(movie) }
        // Salvar os filmes no banco de dados
        movieService.saveAll(apiMoviesMappedToEntity.map { movieMapper.fromEntityToDomain(it) })
    }

    /**
     * Este método realiza a verificação da autenticação com a API do TMDB.
     *
     * @return Retorna um objeto do tipo AuthenticationData.
     */
    fun authentication(): AuthenticationApiData? {
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
    fun getAllMovies(): List<MovieApiData> {
        var movieApiData = emptyList<MovieApiData>()
        val call = tmdbService.getAllMovies()

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
     * Este método realiza a busca de todas as séries de TV disponíveis na API do TMDB.
     *
     * @return Retorna uma lista de objetos do tipo TvData.
     */
    fun getAllTvShows(): List<TvApiData> {
        var tvApiData = emptyList<TvApiData>()
        val call = tmdbService.getAllTvShows()

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
            author = "", authorDetails = AuthorApiData(
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