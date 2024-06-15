package com.github.faening.engsofttmdb.domain.mapper

import com.github.faening.engsofttmdb.data.model.api.genres.GenreApiData
import com.github.faening.engsofttmdb.data.model.db.GenreEntity
import com.github.faening.engsofttmdb.domain.contract.BaseMapper
import com.github.faening.engsofttmdb.domain.model.Genre
import org.springframework.stereotype.Service

@Service
class GenreMapper : BaseMapper<GenreApiData, GenreEntity, Genre> {

    override fun fromApiDataToEntity(data: GenreApiData): GenreEntity {
        return GenreEntity(
            id = null,
            tmdbId = data.id,
            name = data.name,
            movies = mutableSetOf(),
            metadata = null,
        )
    }

    override fun fromEntityToDomain(entity: GenreEntity): Genre {
        return Genre(
            id = entity.id,
            tmdbId = entity.tmdbId,
            name = entity.name,
            createdAt = entity.metadata?.createdAt ,
            updatedAt = entity.metadata?.updatedAt,
        )
    }

    override fun fromDomainToEntity(domain: Genre): GenreEntity {
        return GenreEntity(
            id = domain.id ?: 0,
            tmdbId = domain.tmdbId ?: 0,
            name = domain.name,
            movies = mutableSetOf(),
            metadata = null,
        )
    }

    @Suppress("USELESS_ELVIS")
    override fun mergeEntityAndRequest(entity: GenreEntity, request: Genre): GenreEntity {
        return GenreEntity(
            id = entity.id,
            tmdbId = request.tmdbId ?: entity.tmdbId,
            name = request.name ?: entity.name,
            movies = entity.movies,
            metadata = entity.metadata,
        )
    }

}