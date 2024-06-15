package com.github.faening.engsofttmdb.data.repository

import com.github.faening.engsofttmdb.data.model.db.AuthorDetailsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface AuthorDetailsRepository : JpaRepository<AuthorDetailsEntity, Long> {

    @Query("SELECT a FROM AuthorDetailsEntity a WHERE LOWER(a.name) = LOWER(:name)")
    fun findByNameIgnoreCase(@Param("name") name: String): AuthorDetailsEntity?

    @Query("SELECT a FROM AuthorDetailsEntity a WHERE LOWER(a.username) = LOWER(:username)")
    fun findByUsernameIgnoreCase(@Param("username") username: String): AuthorDetailsEntity?

}