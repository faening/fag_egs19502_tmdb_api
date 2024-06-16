package com.github.faening.engsofttmdb.domain.mapper

import com.github.faening.engsofttmdb.data.model.api.videos.VideoApiData
import com.github.faening.engsofttmdb.data.model.db.MetadataEntity
import com.github.faening.engsofttmdb.data.model.db.VideoEntity
import com.github.faening.engsofttmdb.data.repository.MovieRepository
import com.github.faening.engsofttmdb.domain.contract.BaseMapper
import com.github.faening.engsofttmdb.domain.model.Video
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.OffsetDateTime

@Service
class VideoMapper(
    private val movieRepository: MovieRepository
) : BaseMapper<VideoApiData, VideoEntity, Video> {

    override fun fromApiDataToEntity(data: VideoApiData): VideoEntity {
        return VideoEntity(
            id = null,
            movie = null,
            iso6391 = data.iso6391,
            iso31661 = data.iso31661,
            name = data.name,
            videoKey = data.key,
            site = data.site,
            size = data.size,
            type = data.type,
            official = data.official,
            publishedAt = data.publishedAt,
            tmdbId = data.tmdbId,
            metadata = MetadataEntity(
                createdAt = OffsetDateTime.parse(data.publishedAt).toLocalDateTime() ?: LocalDateTime.now(),
                updatedAt = OffsetDateTime.parse(data.publishedAt).toLocalDateTime() ?: LocalDateTime.now()
            )
        )
    }

    override fun fromEntityToDomain(entity: VideoEntity): Video {
        return Video(
            id = entity.id,
            movieId = entity.movie?.id,
            iso6391 = entity.iso6391,
            iso31661 = entity.iso31661,
            name = entity.name,
            videoKey = entity.videoKey,
            site = entity.site,
            size = entity.size,
            type = entity.type,
            official = entity.official,
            publishedAt = entity.publishedAt,
            tmdbId = entity.tmdbId,
            createdAt = entity.metadata?.createdAt,
            updatedAt = entity.metadata?.updatedAt
        )
    }

    override fun fromDomainToEntity(domain: Video): VideoEntity {
        val movieEntity = movieRepository.findById(domain.movieId!!).get()

        return VideoEntity(
            id = domain.id,
            movie = movieEntity,
            iso6391 = domain.iso6391,
            iso31661 = domain.iso31661,
            name = domain.name,
            videoKey = domain.videoKey,
            site = domain.site,
            size = domain.size,
            type = domain.type,
            official = domain.official,
            publishedAt = domain.publishedAt,
            tmdbId = domain.tmdbId,
            metadata = MetadataEntity(
                createdAt = domain.createdAt ?: LocalDateTime.now(),
                updatedAt = domain.updatedAt ?: LocalDateTime.now()
            )
        )
    }

    override fun mergeEntityAndRequest(entity: VideoEntity, request: Video): VideoEntity {
        return VideoEntity(
            id = entity.id,
            movie = entity.movie,
            iso6391 = request.iso6391,
            iso31661 = request.iso31661,
            name = request.name,
            videoKey = request.videoKey,
            site = request.site,
            size = request.size,
            type = request.type,
            official = request.official,
            publishedAt = request.publishedAt,
            tmdbId = request.tmdbId,
            metadata = MetadataEntity(
                createdAt = entity.metadata?.createdAt ?: LocalDateTime.now(),
                updatedAt = request.updatedAt ?: LocalDateTime.now()
            )
        )
    }

}