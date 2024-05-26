package com.github.faening.engsofttmdb.data.repository

import com.github.faening.engsofttmdb.data.model.db.MovieEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MovieRepository : JpaRepository<MovieEntity, Long>