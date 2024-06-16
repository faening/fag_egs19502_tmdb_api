package com.github.faening.engsofttmdb.data.repository

import com.github.faening.engsofttmdb.data.model.db.VideoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VideoRepository : JpaRepository<VideoEntity, Long>