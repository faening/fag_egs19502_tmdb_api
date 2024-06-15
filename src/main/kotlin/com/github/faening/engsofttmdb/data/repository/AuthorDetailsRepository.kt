package com.github.faening.engsofttmdb.data.repository

import com.github.faening.engsofttmdb.data.model.db.AuthorDetailsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface AuthorDetailsRepository : JpaRepository<AuthorDetailsEntity, Long> {

    @Query("SELECT a FROM AuthorDetailsEntity a WHERE LOWER(a.name) = LOWER(:name) OR LOWER(a.username) = LOWER(:username)")
    fun findByNameOrUsernameIgnoreCase(@Param("name") name: String, @Param("username") username: String): AuthorDetailsEntity?

}