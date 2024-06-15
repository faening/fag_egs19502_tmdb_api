package com.github.faening.engsofttmdb.domain.mapper

import com.github.faening.engsofttmdb.data.model.api.AuthorDetailsApiData
import com.github.faening.engsofttmdb.data.model.db.AuthorDetailsEntity
import com.github.faening.engsofttmdb.data.model.db.MetadataEntity
import com.github.faening.engsofttmdb.data.repository.AuthorDetailsRepository
import com.github.faening.engsofttmdb.domain.contract.BaseMapper
import com.github.faening.engsofttmdb.domain.model.AuthorDetails
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AuthorDetailsMapper : BaseMapper<AuthorDetailsApiData, AuthorDetailsEntity, AuthorDetails> {

    override fun fromApiDataToEntity(data: AuthorDetailsApiData): AuthorDetailsEntity {
        val name = if (data.name.isNullOrBlank()) data.username else data.name
        val username = if (data.username.isNullOrBlank()) data.name else data.username

        return AuthorDetailsEntity(
            id = null,
            name = name ?: "Unknown",
            username = username ?: "Unknown",
            avatarPath = data.avatarPath ?: " ",
            rating = data.rating ?: 0,
            metadata = MetadataEntity(
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
        )
    }

    override fun fromEntityToDomain(entity: AuthorDetailsEntity): AuthorDetails {
        return AuthorDetails(
            id = entity.id,
            name = entity.name,
            username = entity.username,
            avatarPath = entity.avatarPath,
            rating = entity.rating,
            createdAt = entity.metadata?.createdAt,
            updatedAt = entity.metadata?.updatedAt
        )
    }

    override fun fromDomainToEntity(domain: AuthorDetails): AuthorDetailsEntity {
        return AuthorDetailsEntity(
            id = domain.id,
            name = domain.name,
            username = domain.username,
            avatarPath = domain.avatarPath,
            rating = domain.rating,
            metadata = MetadataEntity(
                createdAt = domain.createdAt ?: LocalDateTime.now(),
                updatedAt = domain.updatedAt ?: LocalDateTime.now()
            )
        )
    }

    override fun mergeEntityAndRequest(entity: AuthorDetailsEntity, request: AuthorDetails): AuthorDetailsEntity {
        return AuthorDetailsEntity(
            id = entity.id,
            name = request.name ?: entity.name,
            username = request.username ?: entity.username,
            avatarPath = request.avatarPath ?: entity.avatarPath,
            rating = request.rating ?: entity.rating,
            metadata = MetadataEntity(
                createdAt = entity.metadata?.createdAt ?: LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
        )
    }

}