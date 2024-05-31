package com.github.faening.engsofttmdb.domain.mapper

import com.github.faening.engsofttmdb.data.model.api.MovieApiData
import com.github.faening.engsofttmdb.data.model.db.MovieEntity
import com.github.faening.engsofttmdb.data.repository.GenreRepository
import com.github.faening.engsofttmdb.domain.model.Movie
import org.springframework.stereotype.Service
import java.time.LocalDate

@Suppress("unused")
@Service
class MovieMapper(
    private val genreRepository: GenreRepository
) : BaseMapper<MovieApiData, MovieEntity, Movie>() {

    override fun fromApiDataToEntity(data: MovieApiData): MovieEntity {
        val movieEntity = MovieEntity(
            id = null,
            adult = data.adult,
            backdropPath = data.backdropPath,
            genres = emptySet(),
            tmdbId = data.id,
            originalLanguage = data.originalLanguage,
            originalTitle = data.originalTitle,
            overview = data.overview,
            popularity = data.popularity,
            posterPath = data.posterPath,
            releaseDate = LocalDate.parse(data.releaseDate),
            title = data.title,
            video = data.video,
            voteAverage = data.voteAverage,
            voteCount = data.voteCount,
            metadata = null,
        )

        // Buscar os gêneros do banco de dados que correspondem aos IDs da API
        val genres = genreRepository.findAllById(data.genreIds)

        // Vincular os gêneros ao filme
        movieEntity.genres = genres.toSet()

        return movieEntity
    }

    @Suppress("UNCHECKED_CAST")
    override fun fromEntityToDomain(entity: MovieEntity): Movie {
        return Movie(
            id = entity.id,
            adult = entity.adult,
            backdropPath = entity.backdropPath,
            genreIds =  entity.genres.map { it.id } as List<Long>,
            idTmdb = entity.tmdbId ?: 0,
            originalLanguage = entity.originalLanguage,
            originalTitle = entity.originalTitle,
            overview = entity.overview,
            popularity = entity.popularity,
            posterPath = entity.posterPath,
            releaseDate = entity.releaseDate,
            title = entity.title,
            video = entity.video,
            voteAverage = entity.voteAverage,
            voteCount = entity.voteCount,
            createdAt = entity.metadata?.createdAt,
            updatedAt = entity.metadata?.updatedAt,
        )
    }

    override fun fromDomainToEntity(domain: Movie): MovieEntity {
        val movieEntity = MovieEntity(
            id = domain.id ?: 0,
            adult = domain.adult ?: false,
            backdropPath = domain.backdropPath,
            genres = emptySet(),
            tmdbId = domain.idTmdb,
            originalLanguage = domain.originalLanguage ?: "",
            originalTitle = domain.originalTitle ?: "",
            overview = domain.overview ?: "",
            popularity = domain.popularity ?: 0.0,
            posterPath = domain.posterPath,
            releaseDate = domain.releaseDate,
            title = domain.title,
            video = domain.video ?: false,
            voteAverage = domain.voteAverage ?: 0.0,
            voteCount = domain.voteCount ?: 0,
            metadata = null,
        )

        // Buscar os gêneros do banco de dados que correspondem aos IDs da API
        val genres = genreRepository.findAllById(domain.genreIds)

        // Vincular os gêneros ao filme
        movieEntity.genres = genres.toSet()

        return movieEntity
    }

}