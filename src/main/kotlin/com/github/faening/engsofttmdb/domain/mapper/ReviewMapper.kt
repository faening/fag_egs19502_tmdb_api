package com.github.faening.engsofttmdb.domain.mapper

import com.github.faening.engsofttmdb.data.model.api.ReviewApiData
import com.github.faening.engsofttmdb.data.model.db.MetadataEntity
import com.github.faening.engsofttmdb.data.model.db.ReviewEntity
import com.github.faening.engsofttmdb.data.repository.AuthorDetailsRepository
import com.github.faening.engsofttmdb.domain.model.Review
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ReviewMapper(
    private val authorDetailsRepository: AuthorDetailsRepository,
    private val authorDetailsMapper: AuthorDetailsMapper
) : BaseMapper<ReviewApiData, ReviewEntity, Review> {

    override fun fromApiDataToEntity(data: ReviewApiData): ReviewEntity {
        val authorDetailsEntity = authorDetailsRepository.findByNameIgnoreCase(data.author)
            ?: authorDetailsRepository.findByUsernameIgnoreCase(data.author)

        return ReviewEntity(
            id = null,
            author = data.author,
            authorDetailsId = authorDetailsEntity,
            content = data.content,
            tmdbId = data.tmdbId,
            url = data.url,
            metadata = MetadataEntity(
                createdAt = LocalDateTime.parse(data.createdAt) ?: LocalDateTime.now(),
                updatedAt = LocalDateTime.parse(data.updatedAt) ?: LocalDateTime.now()
            )
        )
    }

    override fun fromEntityToDomain(entity: ReviewEntity): Review {
        val authorDetailsEntity = authorDetailsRepository.findById(entity.authorDetailsId?.id!!).get()
        val authorDetails = authorDetailsMapper.fromEntityToDomain(authorDetailsEntity)

        return Review(
            id = entity.id,
            author = entity.author,
            authorDetails = authorDetails,
            content = entity.content,
            tmdbId = entity.tmdbId,
            url = entity.url,
            createdAt = entity.metadata?.createdAt,
            updatedAt = entity.metadata?.updatedAt
        )
    }

    override fun fromDomainToEntity(domain: Review): ReviewEntity {
        val authorDetailsEntity = authorDetailsRepository.findById(domain.authorDetails?.id!!).get()

        return ReviewEntity(
            id = domain.id,
            author = domain.author,
            authorDetailsId = authorDetailsEntity,
            content = domain.content,
            tmdbId = domain.tmdbId,
            url = domain.url,
            metadata = MetadataEntity(
                createdAt = domain.createdAt ?: LocalDateTime.now(),
                updatedAt = domain.updatedAt ?: LocalDateTime.now()
            )
        )
    }

    override fun mergeEntityAndRequest(entity: ReviewEntity, request: Review): ReviewEntity {
        val authorDetailsEntity = authorDetailsRepository.findById(request.authorDetails?.id!!).get()

        return ReviewEntity(
            id = entity.id,
            author = request.author,
            authorDetailsId = authorDetailsEntity,
            content = request.content,
            tmdbId = request.tmdbId,
            url = request.url,
            metadata = MetadataEntity(
                createdAt = entity.metadata?.createdAt ?: LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
        )
    }

}