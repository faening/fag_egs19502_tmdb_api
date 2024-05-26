package com.github.faening.engsofttmdb.domain.mapper

import com.github.faening.engsofttmdb.data.model.api.GenreApiData
import com.github.faening.engsofttmdb.data.model.db.GenreEntity
import com.github.faening.engsofttmdb.domain.model.Genre

@Suppress("unused")
class GenreMapper : BaseMapper<GenreApiData, GenreEntity, Genre>() {

    override fun fromApiDataToEntity(data: GenreApiData): GenreEntity {
        return GenreEntity(
            id = null,
            tmdbId = data.id,
            name = data.name,
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
            metadata = null,
        )
    }

}