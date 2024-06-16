package com.github.faening.engsofttmdb.data.repository

import com.github.faening.engsofttmdb.data.model.db.CrewEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CrewRepository : JpaRepository<CrewEntity, Long>