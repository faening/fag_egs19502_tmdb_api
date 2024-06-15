package com.github.faening.engsofttmdb.domain.mapper

import com.github.faening.engsofttmdb.data.model.api.ReviewApiData
import com.github.faening.engsofttmdb.data.model.db.MetadataEntity
import com.github.faening.engsofttmdb.data.model.db.ReviewEntity
import com.github.faening.engsofttmdb.data.repository.AuthorDetailsRepository
import com.github.faening.engsofttmdb.data.repository.MovieRepository
import com.github.faening.engsofttmdb.domain.contract.BaseMapper
import com.github.faening.engsofttmdb.domain.model.Review
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.OffsetDateTime

@Service
class ReviewMapper(
    private val authorDetailsRepository: AuthorDetailsRepository,
    private val authorDetailsMapper: AuthorDetailsMapper,
    private val movieRepository: MovieRepository
) : BaseMapper<ReviewApiData, ReviewEntity, Review> {

    override fun fromApiDataToEntity(data: ReviewApiData): ReviewEntity {
        val authorDetailsEntity = authorDetailsRepository.findByNameOrUsernameIgnoreCase(data.author, data.author)

        return ReviewEntity(
            id = null,
            author = data.author,
            authorDetails = authorDetailsEntity,
            content = data.content,
            tmdbId = data.tmdbId,
            url = data.url,
            movie = null,
            metadata = MetadataEntity(
                createdAt = OffsetDateTime.parse(data.createdAt).toLocalDateTime() ?: LocalDateTime.now(),
                updatedAt = OffsetDateTime.parse(data.updatedAt).toLocalDateTime() ?: LocalDateTime.now()
            )
        )
    }

    override fun fromEntityToDomain(entity: ReviewEntity): Review {
        val authorDetailsEntity = authorDetailsRepository.findById(entity.authorDetails?.id!!).get()
        val authorDetails = authorDetailsMapper.fromEntityToDomain(authorDetailsEntity)

        return Review(
            id = entity.id,
            author = entity.author,
            authorDetails = authorDetails,
            content = entity.content,
            tmdbId = entity.tmdbId,
            url = entity.url,
            movieId = entity.movie?.id,
            createdAt = entity.metadata?.createdAt,
            updatedAt = entity.metadata?.updatedAt
        )
    }

    override fun fromDomainToEntity(domain: Review): ReviewEntity {
        val authorDetailsEntity = authorDetailsRepository.findById(domain.authorDetails?.id!!).get()
        val movieEntity = movieRepository.findById(domain.movieId!!).get()

        return ReviewEntity(
            id = domain.id,
            author = domain.author,
            authorDetails = authorDetailsEntity,
            content = domain.content,
            tmdbId = domain.tmdbId,
            url = domain.url,
            movie = movieEntity,
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
            authorDetails = authorDetailsEntity,
            content = request.content,
            tmdbId = request.tmdbId,
            url = request.url,
            movie = entity.movie,
            metadata = MetadataEntity(
                createdAt = entity.metadata?.createdAt ?: LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
        )
    }

}