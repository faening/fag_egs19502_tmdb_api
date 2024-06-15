package com.github.faening.engsofttmdb.data.repository

import com.github.faening.engsofttmdb.data.model.db.ReviewEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReviewRepository : JpaRepository<ReviewEntity, Long>