package com.github.faening.engsofttmdb.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.faening.engsofttmdb.domain.model.Crew
import com.github.faening.engsofttmdb.domain.service.CrewService
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
class CrewControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private var objectMapper: ObjectMapper
) {

    @MockBean
    private lateinit var crewService: CrewService

    @Test
    @DisplayName("Devem retornar status 200 ao buscar todos os diretores")
    fun testGetAll() {
        mockMvc.perform(
            get("/crews")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
    }

    @Test
    @DisplayName("Devem retornar status 200 ao buscar um diretor por ID")
    fun testGetById() {
        val expectedResponse = Crew(
            id = 1L,
            adult = false,
            gender = 1,
            tmdbId = 56322,
            knownForDepartment = "Acting",
            name = "Amy Poehler",
            originalName = "",
            popularity = 26.804,
            profilePath = "/rwmvRonpluV6dCPiQissYrchvSD.jpg",
            creditId = "631bd7450bb076007b78d023",
            department = "Directing",
            job = "Director",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        `when`(crewService.getById(1L)).thenReturn(expectedResponse)

        mockMvc.perform(
            get("/crews/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(
                content().json(objectMapper.writeValueAsString(expectedResponse))
            )
    }

    @Test
    @DisplayName("Devem retornar status 200 ao cadastrar um diretor")
    fun testCreate() {
        val request = """
        {
            "adult": false,
            "gender": 1,
            "tmdbId": 56322,
            "knownForDepartment": "Acting",
            "name": "Amy Poehler",
            "originalName": "Amy Poehler",
            "popularity": 26.804,
            "profilePath": "/rwmvRonpluV6dCPiQissYrchvSD.jpg",
            "creditId": "631bd7450bb076007b78d023",
            "department": "Directing",
            "job": "Director"
        }
        """.trimIndent()

        val expectedResponse = Crew(
            id = 1L,
            adult = false,
            gender = 1,
            tmdbId = 56322,
            knownForDepartment = "Acting",
            name = "Amy Poehler",
            originalName = "",
            popularity = 26.804,
            profilePath = "/rwmvRonpluV6dCPiQissYrchvSD.jpg",
            creditId = "631bd7450bb076007b78d023",
            department = "Directing",
            job = "Director",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        val requestObject = objectMapper.readValue(request, Crew::class.java)
        `when`(crewService.save(requestObject)).thenReturn(expectedResponse)

        mockMvc.perform(
            post("/crews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        )
            .andExpect(status().isOk)
            .andExpect(
                content().json(objectMapper.writeValueAsString(expectedResponse))
            )
    }

    @Test
    @DisplayName("Devem retornar status 200 ao atualizar um diretor")
    fun testUpdate() {
        val request = """
        {
            "adult": false,
            "gender": 1,
            "tmdbId": 56322,
            "knownForDepartment": "Acting",
            "name": "Amy Poehler",
            "originalName": "Amy Poehler",
            "popularity": 26.804,
            "profilePath": "/rwmvRonpluV6dCPiQissYrchvSD.jpg",
            "creditId": "631bd7450bb076007b78d023",
            "department": "Directing",
            "job": "Director"
        }
        """.trimIndent()

        val expectedResponse = Crew(
            id = 1L,
            adult = false,
            gender = 1,
            tmdbId = 56322,
            knownForDepartment = "Acting",
            name = "Amy Poehler",
            originalName = "",
            popularity = 26.804,
            profilePath = "/rwmvRonpluV6dCPiQissYrchvSD.jpg",
            creditId = "631bd7450bb076007b78d023",
            department = "Directing",
            job = "Director",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        val requestObject = objectMapper.readValue(request, Crew::class.java)
        `when`(crewService.update(1L, requestObject)).thenReturn(expectedResponse)

        mockMvc.perform(
            patch("/crews/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        )
            .andExpect(status().isOk)
            .andExpect(
                content().json(objectMapper.writeValueAsString(expectedResponse))
            )
    }

    @Test
    @DisplayName("Devem retornar status 200 ao deletar um diretor")
    fun testDelete() {
        `when`(crewService.delete(1L)).thenReturn(true)

        mockMvc.perform(
            delete("/crews/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isNoContent)
    }

}