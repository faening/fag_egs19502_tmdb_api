package com.github.faening.engsofttmdb.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.faening.engsofttmdb.domain.model.Genre
import com.github.faening.engsofttmdb.domain.service.GenreService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class GenreControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private var objectMapper: ObjectMapper
) {

    @MockBean
    private lateinit var genreService: GenreService

    @Test
    @DisplayName("Devem retornar status 200 ao buscar todos os gêneros")
    fun testGetAll() {
        mockMvc.perform(
            get("/genres")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
    }

    @Test
    @DisplayName("Devem retornar status 200 ao buscar um gênero por ID")
    fun testGetById() {
        val expectedResponse = Genre(
            id = 1L,
            name = "Action",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        `when`(genreService.getById(1L)).thenReturn(expectedResponse)

        mockMvc.perform(
            get("/genres/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(
                content().json(objectMapper.writeValueAsString(expectedResponse))
            )
    }

    @Test
    @DisplayName("Deve retornar status 200 ao cadastrar um gênero")
    fun testCreate() {
        val request = """
        {
            "tmdbId": 72,
            "name": "Action"
        }
        """.trimIndent()

        val expectedResponse = Genre(
            id = 1L,
            name = "Action",
            tmdbId = 72,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        val requestObject = objectMapper.readValue(request, Genre::class.java)
        `when`(genreService.save(requestObject)).thenReturn(expectedResponse)

        mockMvc.perform(
            post("/genres")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        )
            .andExpect(status().isOk)
            .andExpect(
                content().json(objectMapper.writeValueAsString(expectedResponse))
            )
    }

    @Test
    @DisplayName("Deve retornar status 200 ao atualizar um gênero")
    fun testUpdate() {
        val request = """
        {
            "tmdbId": 72,
            "name": "Action"
        }
        """.trimIndent()

        val expectedResponse = Genre(
            id = 1L,
            name = "Action",
            tmdbId = 72,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        val requestObject = objectMapper.readValue(request, Genre::class.java)
        `when`(genreService.update(1L, requestObject)).thenReturn(expectedResponse)

        mockMvc.perform(
            patch("/genres/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        )
            .andExpect(status().isOk)
            .andExpect(
                content().json(objectMapper.writeValueAsString(expectedResponse))
            )
    }

    @Test
    @DisplayName("Deve retornar status 204 ao deletar um gênero")
    fun testDelete() {
        `when`(genreService.delete(1L)).thenReturn(true)

        mockMvc.perform(
            delete("/genres/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isNoContent)
    }

}