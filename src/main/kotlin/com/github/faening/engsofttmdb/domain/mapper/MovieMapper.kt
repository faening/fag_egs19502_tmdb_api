package com.github.faening.engsofttmdb.domain.mapper

import com.github.faening.engsofttmdb.data.model.api.movie.MovieApiData
import com.github.faening.engsofttmdb.data.model.db.GenreEntity
import com.github.faening.engsofttmdb.data.model.db.MetadataEntity
import com.github.faening.engsofttmdb.data.model.db.MovieEntity
import com.github.faening.engsofttmdb.data.repository.GenreRepository
import com.github.faening.engsofttmdb.domain.contract.BaseMapper
import com.github.faening.engsofttmdb.domain.model.Movie
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class MovieMapper @Autowired constructor(
    private val genreRepository: GenreRepository,
    private val genreMapper: GenreMapper,
) : BaseMapper<MovieApiData, MovieEntity, Movie> {

    override fun fromApiDataToEntity(data: MovieApiData): MovieEntity {
        val genres: MutableSet<GenreEntity> = data.genreIds.mapNotNull {
            genreRepository.findByTmdbId(it)
        }.toMutableSet()

        return MovieEntity(
            id = null,
            tmdbId = data.id,
            adult = data.adult,
            backdropPath = data.backdropPath,
            genres = genres,
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
            metadata = MetadataEntity(
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
        )
    }

    override fun fromEntityToDomain(entity: MovieEntity): Movie {
        return Movie(
            id = entity.id,
            adult = entity.adult,
            backdropPath = entity.backdropPath,
            genreIds = entity.genres.map { genreMapper.fromEntityToDomain(it).id ?: 0 },
            tmdbId = entity.tmdbId ?: 0,
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
        val genres = domain.genreIds.mapNotNull {
            genreRepository.findById(it).orElse(null)
        }.toMutableSet()

        return MovieEntity(
            id = domain.id ?: 0,
            adult = domain.adult ?: false,
            backdropPath = domain.backdropPath,
            genres = genres,
            tmdbId = domain.tmdbId,
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
    }

    @Suppress("USELESS_ELVIS")
    override fun mergeEntityAndRequest(entity: MovieEntity, request: Movie): MovieEntity {
        val genres = request.genreIds.mapNotNull {
            genreRepository.findById(it).orElse(null)
        }.toMutableSet()

        return MovieEntity(
            id = entity.id,
            adult = request.adult ?: entity.adult,
            backdropPath = request.backdropPath ?: entity.backdropPath,
            genres = genres ?: entity.genres,
            tmdbId = request.tmdbId ?: entity.tmdbId,
            originalLanguage = request.originalLanguage ?: entity.originalLanguage,
            originalTitle = request.originalTitle ?: entity.originalTitle,
            overview = request.overview ?: entity.overview,
            popularity = request.popularity ?: entity.popularity,
            posterPath = request.posterPath ?: entity.posterPath,
            releaseDate = request.releaseDate ?: entity.releaseDate,
            title = request.title ?: entity.title,
            video = request.video ?: entity.video,
            voteAverage = request.voteAverage ?: entity.voteAverage,
            voteCount = request.voteCount ?: entity.voteCount,
            metadata = entity.metadata,
        )
    }

}