package com.github.faening.engsofttmdb.domain.mapper

import com.github.faening.engsofttmdb.data.model.api.credits.CastApiData
import com.github.faening.engsofttmdb.data.model.db.CastEntity
import com.github.faening.engsofttmdb.data.model.db.MetadataEntity
import com.github.faening.engsofttmdb.domain.contract.BaseMapper
import com.github.faening.engsofttmdb.domain.model.Cast
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CastMapper : BaseMapper<CastApiData, CastEntity, Cast> {

    override fun fromApiDataToEntity(data: CastApiData): CastEntity {
        return CastEntity(
            id = null,
            adult = data.adult,
            gender = data.gender,
            tmdbId = data.id,
            knownForDepartment = data.knownForDepartment,
            name = data.name,
            originalName = data.originalName,
            popularity = data.popularity,
            profilePath = data.profilePath,
            castId = data.castId,
            character = data.character,
            creditId = data.creditId,
            order = data.order,
            movies = mutableSetOf(),
            metadata = MetadataEntity(
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
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
            originalName = entity.originalName,
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
            originalName = domain.originalName,
            popularity = domain.popularity,
            profilePath = domain.profilePath,
            castId = domain.castId,
            character = domain.character,
            creditId = domain.creditId,
            order = domain.order,
            movies = mutableSetOf(),
            metadata = MetadataEntity(
                createdAt = domain.createdAt ?: LocalDateTime.now(),
                updatedAt = domain.updatedAt ?: LocalDateTime.now()
            )
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
            originalName = request.originalName ?: entity.originalName,
            popularity = request.popularity ?: entity.popularity,
            profilePath = request.profilePath ?: entity.profilePath,
            castId = request.castId ?: entity.castId,
            character = request.character ?: entity.character,
            creditId = request.creditId ?: entity.creditId,
            order = request.order ?: entity.order,
            movies = entity.movies,
            metadata = MetadataEntity(
                createdAt = entity.metadata?.createdAt ?: LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
        )
    }

}