package com.github.faening.engsofttmdb.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.faening.engsofttmdb.domain.model.AuthorDetails
import com.github.faening.engsofttmdb.domain.model.Review
import com.github.faening.engsofttmdb.domain.service.ReviewService
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
class ReviewControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private var objectMapper: ObjectMapper
) {

    @MockBean
    private lateinit var reviewService: ReviewService

    @Test
    @DisplayName("Devem retornar status 200 ao buscar todos os comentários")
    fun testGetAll() {
        mockMvc.perform(
            get("/reviews")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
    }

    @Test
    @DisplayName("Devem retornar status 200 ao buscar um comentário por ID")
    fun testGetById() {
        val expectedResponse = Review(
            id = 1L,
            movieId = 1,
            author = "John Doe",
            authorDetails = AuthorDetails(
                id = 1L,
                name = "johndoe",
                username = "johndoe",
                avatarPath = "/avatar.jpg",
                rating = 8,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            ),
            content = "This is a review",
            tmdbId = "6669f31a282af652f2d05392",
            url =  "https://www.themoviedb.org/review/6669f31a282af652f2d05392",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        `when`(reviewService.getById(1L)).thenReturn(expectedResponse)

        mockMvc.perform(
            get("/reviews/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(
                content().json(objectMapper.writeValueAsString(expectedResponse))
            )
    }

    @Test
    @DisplayName("Devem retornar status 200 ao criar um comentário")
    fun testCreate() {
        val request = """
        {
            "author": "John Doe",
            "authorDetails": {
                "name": "johndoe",
                "username": "johndoe",
                "avatarPath": "/avatar.jpg",
                "rating": 8
            },
            "content": "This is a review",
            "tmdbId": "6669f31a282af652f2d05392",
            "url": "https://www.themoviedb.org/review/6669f31a282af652f2d05392"
        }
        """.trimIndent()

        val expectedResponse = Review(
            id = 1L,
            movieId = 1,
            author = "John Doe",
            authorDetails = AuthorDetails(
                id = 1L,
                name = "johndoe",
                username = "johndoe",
                avatarPath = "/avatar.jpg",
                rating = 8,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            ),
            content = "This is a review",
            tmdbId = "6669f31a282af652f2d05392",
            url =  "https://www.themoviedb.org/review/6669f31a282af652f2d05392",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        val requestObject = objectMapper.readValue(request, Review::class.java)
        `when`(reviewService.save(requestObject)).thenReturn(expectedResponse)

        mockMvc.perform(
            post("/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        )
            .andExpect(status().isOk)
            .andExpect(
                content().json(objectMapper.writeValueAsString(expectedResponse))
            )
    }

    @Test
    @DisplayName("Devem retornar status 200 ao atualizar um comentário")
    fun testUpdate() {
        val request = """
        {
            "author": "John Doe",
            "authorDetails": {
                "name": "johndoe",
                "username": "johndoe",
                "avatarPath": "/avatar.jpg",
                "rating": 8
            },
            "content": "This is a review",
            "tmdbId": "6669f31a282af652f2d05392",
            "url": "https://www.themoviedb.org/review/6669f31a282af652f2d05392"
        }
        """.trimIndent()

        val expectedResponse = Review(
            id = 1L,
            movieId = 1,
            author = "John Doe",
            authorDetails = AuthorDetails(
                id = 1L,
                name = "johndoe",
                username = "johndoe",
                avatarPath = "/avatar.jpg",
                rating = 8,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            ),
            content = "This is a review",
            tmdbId = "6669f31a282af652f2d05392",
            url =  "https://www.themoviedb.org/review/6669f31a282af652f2d05392",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        val requestObject = objectMapper.readValue(request, Review::class.java)
        `when`(reviewService.update(1L, requestObject)).thenReturn(expectedResponse)

        mockMvc.perform(
            patch("/reviews/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        )
            .andExpect(status().isOk)
            .andExpect(
                content().json(objectMapper.writeValueAsString(expectedResponse))
            )
    }

    @Test
    @DisplayName("Devem retornar status 200 ao deletar um comentário")
    fun testDelete() {
        `when`(reviewService.delete(1L)).thenReturn(true)

        mockMvc.perform(
            delete("/reviews/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isNoContent)
    }

}