package com.github.faening.engsofttmdb.data.repository

import com.github.faening.engsofttmdb.data.model.db.CastEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CastRepository : JpaRepository<CastEntity, Long> {
    fun existsByTmdbId(tmdbId: Long): Boolean
    fun findByTmdbId(tmdbId: Long): CastEntity?
}