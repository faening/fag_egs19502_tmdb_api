package com.github.faening.engsofttmdb.data.repository

import com.github.faening.engsofttmdb.data.model.db.AuthorDetailsEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorDetailsRepository : JpaRepository<AuthorDetailsEntity, Long>