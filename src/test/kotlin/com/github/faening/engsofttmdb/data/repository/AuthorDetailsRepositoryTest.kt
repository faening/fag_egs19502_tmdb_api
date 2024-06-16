package com.github.faening.engsofttmdb.data.repository

import com.github.faening.engsofttmdb.data.model.db.AuthorDetailsEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@ActiveProfiles("test")
class AuthorDetailsRepositoryTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val authorDetailsRepository: AuthorDetailsRepository
) {

    @Test
    @DisplayName("Deve retornar um AuthorDetailsEntity ao buscar por nome ou username")
    fun whenFindByNameOrUsernameIgnoreCase_ThenReturnAuthorDetailsEntity() {
        val author = AuthorDetailsEntity(
            id = null,
            name = "john_doe",
            username = "john_doe",
            avatarPath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
            rating = 5,
            reviews = mutableListOf(),
            metadata = null
        )
        entityManager.persistAndFlush(author)

        val foundAuthorByName = authorDetailsRepository.findByNameOrUsernameIgnoreCase(author.name, "")
        val foundAuthorByUsername = authorDetailsRepository.findByNameOrUsernameIgnoreCase("", author.username)

        assertEquals(author, foundAuthorByName)
        assertEquals(author, foundAuthorByUsername)
    }


}