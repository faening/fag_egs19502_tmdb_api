package com.github.faening.engsofttmdb.data.repository

import com.github.faening.engsofttmdb.data.model.db.GenreEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GenreRepository : JpaRepository<GenreEntity, Long> {
    fun findByTmdbId(tmdbId: Long): GenreEntity?
}