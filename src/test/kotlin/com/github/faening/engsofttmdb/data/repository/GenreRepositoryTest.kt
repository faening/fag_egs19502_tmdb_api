package com.github.faening.engsofttmdb.data.repository

import com.github.faening.engsofttmdb.data.model.db.GenreEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@ActiveProfiles("test")
class GenreRepositoryTest @Autowired constructor(
    val testEntityManager: TestEntityManager,
    val genreRepository: GenreRepository
) {

    @Test
    @DisplayName("Deve retornar um gênero pelo id do TMDB")
    fun testFindByTmdbId() {
        val tmdbId = 1L
        val expectedGenre = GenreEntity(
            id = null,
            tmdbId = 1,
            name = "Action",
            movies = setOf(),
            metadata = null
        )
        testEntityManager.persistAndFlush(expectedGenre)

        val actualGenre = genreRepository.findByTmdbId(tmdbId)

        assertEquals(expectedGenre, actualGenre)
    }

}