package com.github.faening.engsofttmdb.data.repository

import com.github.faening.engsofttmdb.data.model.db.MovieEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository : JpaRepository<MovieEntity, Long> {
    fun findByTmdbId(tmdbId: Long): MovieEntity?
}