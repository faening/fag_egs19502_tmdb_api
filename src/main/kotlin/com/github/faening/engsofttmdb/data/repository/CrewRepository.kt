package com.github.faening.engsofttmdb.data.repository

import com.github.faening.engsofttmdb.data.model.db.CrewEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CrewRepository : JpaRepository<CrewEntity, Long> {
    fun existsByTmdbId(tmdbId: Long): Boolean
    fun findByTmdbId(tmdbId: Long): CrewEntity?
}