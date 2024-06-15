package com.github.faening.engsofttmdb.data.repository

import com.github.faening.engsofttmdb.data.model.db.ReviewEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository : JpaRepository<ReviewEntity, Long>