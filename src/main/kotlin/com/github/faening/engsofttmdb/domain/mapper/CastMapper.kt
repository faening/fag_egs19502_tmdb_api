package com.github.faening.engsofttmdb.domain.mapper

import com.github.faening.engsofttmdb.data.model.api.CastApiData
import com.github.faening.engsofttmdb.data.model.db.CastEntity
import com.github.faening.engsofttmdb.data.model.db.MovieEntity
import com.github.faening.engsofttmdb.domain.model.Cast
import org.springframework.stereotype.Service

@Service
class CastMapper : BaseMapper<CastApiData, CastEntity, Cast>() {

    override fun fromApiDataToEntity(data: CastApiData): CastEntity {
        return CastEntity(
            id = null,
            adult = data.adult,
            gender = data.gender,
            tmdbId = data.id,
            knownForDepartment = data.knownForDepartment,
            name = data.name,
            popularity = data.popularity,
            profilePath = data.profilePath,
            castId = data.castId,
            character = data.character,
            creditId = data.creditId,
            order = data.order,
            movies = mutableSetOf(),
            metadata = null
        )
    }

    override fun fromEntityToDomain(entity: CastEntity): Cast {
        return Cast(
            id = entity.id,
            adult = entity.adult,
            gender = entity.gender,
            tmdbId = entity.tmdbId,
            knownForDepartment = entity.knownForDepartment,
            name = entity.name,
            popularity = entity.popularity,
            profilePath = entity.profilePath,
            castId = entity.castId,
            character = entity.character,
            creditId = entity.creditId,
            order = entity.order,
            createdAt = entity.metadata?.createdAt,
            updatedAt = entity.metadata?.updatedAt,
        )
    }

    override fun fromDomainToEntity(domain: Cast): CastEntity {
        return CastEntity(
            id = domain.id,
            adult = domain.adult,
            gender = domain.gender,
            tmdbId = domain.tmdbId,
            knownForDepartment = domain.knownForDepartment,
            name = domain.name,
            popularity = domain.popularity,
            profilePath = domain.profilePath,
            castId = domain.castId,
            character = domain.character,
            creditId = domain.creditId,
            order = domain.order,
            movies = mutableSetOf(),
            metadata = null
        )
    }

    @Suppress("USELESS_ELVIS")
    override fun mergeEntityAndRequest(entity: CastEntity, request: Cast): CastEntity {
        return CastEntity(
            id = entity.id,
            adult = request.adult ?: entity.adult,
            gender = request.gender ?: entity.gender,
            tmdbId = request.tmdbId ?: entity.tmdbId,
            knownForDepartment = request.knownForDepartment ?: entity.knownForDepartment,
            name = request.name ?: entity.name,
            popularity = request.popularity ?: entity.popularity,
            profilePath = request.profilePath ?: entity.profilePath,
            castId = request.castId ?: entity.castId,
            character = request.character ?: entity.character,
            creditId = request.creditId ?: entity.creditId,
            order = request.order ?: entity.order,
            movies = entity.movies,
            metadata = entity.metadata
        )
    }

    fun addMovieToCastEntity(cast: CastEntity, movie: MovieEntity): CastEntity {
        cast.movies?.add(movie)
        return cast
    }

}