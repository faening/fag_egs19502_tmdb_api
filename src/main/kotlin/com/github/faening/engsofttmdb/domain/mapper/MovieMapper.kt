package com.github.faening.engsofttmdb.domain.mapper

import com.github.faening.engsofttmdb.data.model.api.MovieData
import com.github.faening.engsofttmdb.data.model.db.MovieEntity
import com.github.faening.engsofttmdb.domain.model.Movie

@Suppress("unused")
class MovieMapper : BaseMapper<MovieData, MovieEntity, Movie>() {

    override fun fromApiDataToEntity(data: MovieData): MovieEntity {
        return MovieEntity(
            id = null,
            adult = data.adult,
            backdropPath = data.backdropPath,
            genreIds = data.genreIds,
            idTmdb = data.id,
            originalLanguage = data.originalLanguage,
            originalTitle = data.originalTitle,
            overview = data.overview,
            popularity = data.popularity,
            posterPath = data.posterPath,
            releaseDate = data.releaseDate,
            title = data.title,
            video = data.video,
            voteAverage = data.voteAverage,
            voteCount = data.voteCount,
            metadata = null,
        )
    }

    override fun fromEntityToDomain(entity: MovieEntity): Movie {
        return Movie(
            id = entity.id,
            adult = entity.adult,
            backdropPath = entity.backdropPath,
            genreIds = entity.genreIds,
            idTmdb = entity.idTmdb ?: 0,
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
        return MovieEntity(
            id = domain.id ?: 0,
            adult = domain.adult ?: false,
            backdropPath = domain.backdropPath,
            genreIds = domain.genreIds,
            idTmdb = domain.idTmdb,
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

}