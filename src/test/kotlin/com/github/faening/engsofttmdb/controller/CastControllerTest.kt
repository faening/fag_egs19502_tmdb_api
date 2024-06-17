package com.github.faening.engsofttmdb.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.faening.engsofttmdb.domain.model.Cast
import com.github.faening.engsofttmdb.domain.service.CastService
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
class CastControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private var objectMapper: ObjectMapper
) {

    @MockBean
    private lateinit var castService: CastService

    @Test
    @DisplayName("Devem retornar status 200 ao buscar todos os elencos")
    fun testGetAll() {
        mockMvc.perform(
            get("/casts")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
    }

    @Test
    @DisplayName("Devem retornar status 200 ao buscar um elenco por ID")
    fun testGetById() {
        val expectedResponse = Cast(
            id = 1L,
            adult = false,
            gender = 1,
            tmdbId = 56322,
            knownForDepartment = "Acting",
            name = "Amy Poehler",
            originalName = "",
            popularity = 26.804,
            profilePath = "/rwmvRonpluV6dCPiQissYrchvSD.jpg",
            castId = 4,
            character = "Joy (voice)",
            creditId = "631bd7450bb076007b78d023",
            order = 0,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        `when`(castService.getById(1L)).thenReturn(expectedResponse)

        mockMvc.perform(
            get("/casts/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(
                content().json(objectMapper.writeValueAsString(expectedResponse))
            )
    }

    @Test
    @DisplayName("Devem retornar status 200 ao cadastrar um elenco")
    fun testCreate() {
        val request = """
        {
            "adult": false,
            "gender": 1,
            "tmdbId": 56322,
            "knownForDepartment": "Acting",
            "name": "Amy Poehler",
            "originalName": "",
            "popularity": 26.804,
            "profilePath": "/rwmvRonpluV6dCPiQissYrchvSD.jpg",
            "castId": 4,
            "character": "Joy (voice)",
            "creditId": "631bd7450bb076007b78d023",
            "order": 0
        }
        """.trimIndent()

        val expectedResponse = Cast(
            id = 1L,
            adult = false,
            gender = 1,
            tmdbId = 56322,
            knownForDepartment = "Acting",
            name = "Amy Poehler",
            originalName = "",
            popularity = 26.804,
            profilePath = "/rwmvRonpluV6dCPiQissYrchvSD.jpg",
            castId = 4,
            character = "Joy (voice)",
            creditId = "631bd7450bb076007b78d023",
            order = 0,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        val requestObject = objectMapper.readValue(request, Cast::class.java)
        `when`(castService.save(requestObject)).thenReturn(expectedResponse)

        mockMvc.perform(
            post("/casts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        )
            .andExpect(status().isOk)
            .andExpect(
                content().json(objectMapper.writeValueAsString(expectedResponse))
            )
    }

    @Test
    @DisplayName("Devem retornar status 200 ao atualizar um elenco")
    fun testUpdate() {
        val request = """
        {
            "adult": false,
            "gender": 1,
            "tmdbId": 56322,
            "knownForDepartment": "Acting",
            "name": "Amy Poehler",
            "originalName": "",
            "popularity": 26.804,
            "profilePath": "/rwmvRonpluV6dCPiQissYrchvSD.jpg",
            "castId": 4,
            "character": "Joy (voice)",
            "creditId": "631bd7450bb076007b78d023",
            "order": 0
        }
        """.trimIndent()

        val expectedResponse = Cast(
            id = 1L,
            adult = false,
            gender = 1,
            tmdbId = 56322,
            knownForDepartment = "Acting",
            name = "Amy Poehler",
            originalName = "",
            popularity = 26.804,
            profilePath = "/rwmvRonpluV6dCPiQissYrchvSD.jpg",
            castId = 4,
            character = "Joy (voice)",
            creditId = "631bd7450bb076007b78d023",
            order = 0,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        val requestObject = objectMapper.readValue(request, Cast::class.java)
        `when`(castService.update(1L, requestObject)).thenReturn(expectedResponse)

        mockMvc.perform(
            patch("/casts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        )
            .andExpect(status().isOk)
            .andExpect(
                content().json(objectMapper.writeValueAsString(expectedResponse))
            )
    }

    @Test
    @DisplayName("Devem retornar status 200 ao deletar um elenco")
    fun testDelete() {
        `when`(castService.delete(1L)).thenReturn(true)

        mockMvc.perform(
            delete("/casts/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isNoContent)
    }

}