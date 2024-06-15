package com.github.faening.engsofttmdb.domain.mapper

import com.github.faening.engsofttmdb.data.model.api.credits.CrewApiData
import com.github.faening.engsofttmdb.data.model.db.CrewEntity
import com.github.faening.engsofttmdb.data.model.db.MetadataEntity
import com.github.faening.engsofttmdb.domain.contract.BaseMapper
import com.github.faening.engsofttmdb.domain.model.Crew
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CrewMapper : BaseMapper<CrewApiData, CrewEntity, Crew> {

    override fun fromApiDataToEntity(data: CrewApiData): CrewEntity {
        return CrewEntity(
            id = null,
            adult = data.adult,
            gender = data.gender,
            tmdbId = data.id,
            knownForDepartment = data.knownForDepartment,
            name = data.name,
            originalName = data.originalName,
            popularity = data.popularity,
            profilePath = data.profilePath,
            creditId = data.creditId,
            department = data.department,
            job = data.job,
            movies = mutableSetOf(),
            metadata = MetadataEntity(
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
        )
    }

    override fun fromEntityToDomain(entity: CrewEntity): Crew {
        return Crew(
            id = entity.id,
            adult = entity.adult,
            gender = entity.gender,
            tmdbId = entity.tmdbId,
            knownForDepartment = entity.knownForDepartment,
            name = entity.name,
            originalName = entity.originalName,
            popularity = entity.popularity,
            profilePath = entity.profilePath,
            creditId = entity.creditId,
            department = entity.department,
            job = entity.job,
            createdAt = entity.metadata?.createdAt,
            updatedAt = entity.metadata?.updatedAt,
        )
    }

    override fun fromDomainToEntity(domain: Crew): CrewEntity {
        return CrewEntity(
            id = domain.id,
            adult = domain.adult,
            gender = domain.gender,
            tmdbId = domain.tmdbId,
            knownForDepartment = domain.knownForDepartment,
            name = domain.name,
            originalName = domain.originalName,
            popularity = domain.popularity,
            profilePath = domain.profilePath,
            creditId = domain.creditId,
            department = domain.department,
            job = domain.job,
            movies = mutableSetOf(),
            metadata = MetadataEntity(
                createdAt = domain.createdAt ?: LocalDateTime.now(),
                updatedAt = domain.updatedAt ?: LocalDateTime.now()
            )
        )
    }

    @Suppress("USELESS_ELVIS")
    override fun mergeEntityAndRequest(entity: CrewEntity, request: Crew): CrewEntity {
        return CrewEntity(
            id = entity.id,
            adult = request.adult ?: entity.adult,
            gender = request.gender ?: entity.gender,
            tmdbId = request.tmdbId ?: entity.tmdbId,
            knownForDepartment = request.knownForDepartment ?: entity.knownForDepartment,
            name = request.name ?: entity.name,
            originalName = request.originalName ?: entity.originalName,
            popularity = request.popularity ?: entity.popularity,
            profilePath = request.profilePath ?: entity.profilePath,
            creditId = request.creditId ?: entity.creditId,
            department = request.department ?: entity.department,
            job = request.job ?: entity.job,
            movies = entity.movies,
            metadata = entity.metadata
        )
    }

}