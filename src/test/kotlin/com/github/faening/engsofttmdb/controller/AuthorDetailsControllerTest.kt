package com.github.faening.engsofttmdb.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.faening.engsofttmdb.domain.model.AuthorDetails
import com.github.faening.engsofttmdb.domain.service.AuthorDetailsService
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
class AuthorDetailsControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private var objectMapper: ObjectMapper
) {

    @MockBean
    private lateinit var authorDetailsService: AuthorDetailsService

    @Test
    @DisplayName("Devem retornar status 200 ao buscar todos os autores")
    fun testGetAll() {
        mockMvc.perform(
            get("/author-details")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
    }

    @Test
    @DisplayName("Devem retornar status 200 ao buscar um autor por ID")
    fun testGetById() {
        val expectedResponse = AuthorDetails(
            id = 1L,
            name = "author_details_1",
            username = "biography",
            avatarPath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
            rating = 0,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        `when`(authorDetailsService.getById(1L)).thenReturn(expectedResponse)

        mockMvc.perform(
            get("/author-details/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)))
    }

    @Test
    @DisplayName("Devem retornar status 200 ao cadastrar um autor")
    fun testCreate() {
        // Arrange
        val request = """
        {
            "name": "author_details_1",
            "username": "biography",
            "avatarPath": "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
            "rating": 0
        }
    """.trimIndent()
        val expectedResponse = AuthorDetails(
            id = 1L,
            name = "author_details_1",
            username = "biography",
            avatarPath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
            rating = 0,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        val requestObject = objectMapper.readValue(request, AuthorDetails::class.java)
        `when`(authorDetailsService.save(requestObject)).thenReturn(expectedResponse)

        // Act & Assert
        mockMvc.perform(
            post("/author-details")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        )
            .andExpect(status().isOk)
            .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)))
    }

    @Test
    @DisplayName("Devem retornar status 200 ao atualizar um autor")
    fun testUpdate() {
        val request = """
        {
            "name": "author_details_1",
            "username": "biography",
            "avatarPath": "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
            "rating": 0
        }
    """.trimIndent()
        val expectedResponse = AuthorDetails(
            id = 1L,
            name = "author_details_1",
            username = "biography",
            avatarPath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
            rating = 0,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        val requestObject = objectMapper.readValue(request, AuthorDetails::class.java)
        `when`(authorDetailsService.update(1L, requestObject)).thenReturn(expectedResponse)

        mockMvc.perform(
            patch("/author-details/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        )
            .andExpect(status().isOk)
            .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)))
    }

    @Test
    @DisplayName("Deve retornar status 204 ao deletar um autor")
    fun testDelete() {
        `when`(authorDetailsService.delete(1L)).thenReturn(true)

        mockMvc.perform(
            delete("/author-details/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isNoContent)
    }

}