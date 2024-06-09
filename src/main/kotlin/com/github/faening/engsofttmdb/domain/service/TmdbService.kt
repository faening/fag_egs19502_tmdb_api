package com.github.faening.engsofttmdb.domain.service

import com.github.faening.engsofttmdb.data.api.TmdbApi
import com.github.faening.engsofttmdb.data.model.api.*
import com.github.faening.engsofttmdb.data.model.db.CastEntity
import com.github.faening.engsofttmdb.data.model.db.CrewEntity
import com.github.faening.engsofttmdb.data.model.db.GenreEntity
import com.github.faening.engsofttmdb.data.model.db.MovieEntity
import com.github.faening.engsofttmdb.domain.mapper.CastMapper
import com.github.faening.engsofttmdb.domain.mapper.CrewMapper
import com.github.faening.engsofttmdb.domain.mapper.GenreMapper
import com.github.faening.engsofttmdb.domain.mapper.MovieMapper
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Suppress("unused")
@Service
class TmdbService @Autowired constructor(
    private val tmdbApi: TmdbApi,
    private val genreService: GenreService,
    private val genreMapper: GenreMapper,
    private val movieService: MovieService,
    private val movieMapper: MovieMapper,
    private val castService: CastService,
    private val castMapper: CastMapper,
    private val crewService: CrewService,
    private val crewMapper: CrewMapper
) {

    @PostConstruct
    fun fetchDataFromAPIAndPopulateLocalDatabase() {
        fetchGenresAndSaveInLocalDatabase()
        fetchMoviesAndSaveInLocalDatabase()
    }

    private fun fetchGenresAndSaveInLocalDatabase() {
        val apiGenres: List<GenreApiData> = getAllGenres().genres
        val genresMappedToEntity: List<GenreEntity> = apiGenres.map { genre -> genreMapper.fromApiDataToEntity(genre) }
        genreService.saveAll(genresMappedToEntity.map { genreMapper.fromEntityToDomain(it) })
    }

    private fun fetchMoviesAndSaveInLocalDatabase() {
        val apiMovies: List<MovieApiData> = getAllMovies()
        val moviesMappedToEntity: List<MovieEntity> = apiMovies.map { movie -> movieMapper.fromApiDataToEntity(movie) }
        val moviesWithCredits = fetchMovieCreditsAndSaveInLocalDatabase(moviesMappedToEntity)
        movieService.saveAll(moviesWithCredits.map { movieMapper.fromEntityToDomain(it) })
    }

    private fun fetchMovieCreditsAndSaveInLocalDatabase(movies: List<MovieEntity>) : List<MovieEntity> {
        movies.map { movie ->
            val creditsApiData = getMovieCredits(movie.tmdbId!!)
            val casts: List<CastEntity> = creditsApiData.cast.map { cast -> castMapper.fromApiDataToEntity(cast) }.take(3)
            val crews: List<CrewEntity> = creditsApiData.crew.map { crew -> crewMapper.fromApiDataToEntity(crew) }.take(3)
            movie.casts = saveCastsInLocalDatabase(casts)
            movie.crews = saveCrewsInLocalDatabase(crews)
        }
        return movies
    }

    private fun saveCastsInLocalDatabase(casts: List<CastEntity>): MutableSet<CastEntity> {
        val movieCasts: MutableSet<CastEntity> = mutableSetOf()

        casts.map { cast ->
            val existingCast = castService.findByTmdbId(cast.tmdbId)
            if (existingCast != null) {
                movieCasts.add(existingCast)
            } else {
                val savedCast = castService.saveEntity(cast)
                movieCasts.add(savedCast)
            }
        }

        return movieCasts
    }

    private fun saveCrewsInLocalDatabase(crews: List<CrewEntity>): MutableSet<CrewEntity> {
        val movieCrews: MutableSet<CrewEntity> = mutableSetOf()

        crews.map { crew ->
            val existingCrew = crewService.findByTmdbId(crew.tmdbId)
            if (existingCrew != null) {
                movieCrews.add(existingCrew)
            } else {
                val savedCrew = crewService.saveEntity(crew)
                movieCrews.add(savedCrew)
            }
        }

        return movieCrews
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