package com.github.faening.engsofttmdb.data.repository

import com.github.faening.engsofttmdb.data.model.db.GenreEntity
import org.springframework.data.jpa.repository.JpaRepository

interface GenreRepository : JpaRepository<GenreEntity, Long>