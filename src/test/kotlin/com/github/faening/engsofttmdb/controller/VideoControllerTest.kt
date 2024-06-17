package com.github.faening.engsofttmdb.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.faening.engsofttmdb.domain.model.Video
import com.github.faening.engsofttmdb.domain.service.VideoService
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
class VideoControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private var objectMapper: ObjectMapper
) {

    @MockBean
    private lateinit var videoService: VideoService

    @Test
    @DisplayName("Devem retornar status 200 ao buscar todos os videos")
    fun testGetAll() {
        mockMvc.perform(
            get("/videos")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
    }

    @Test
    @DisplayName("Devem retornar status 200 ao buscar um video por ID")
    fun testGetById() {
        val expectedResponse = Video(
            id = 1L,
            movieId = 1,
            iso6391 = "en",
            iso31661 = "US",
            name = "Meet the Cast of Inside Out 2",
            videoKey = "uyzQw8-8k9U",
            site = "YouTube",
            size = 1080,
            type = "Trailer",
            official = true,
            publishedAt = "2024-06-07T00:37:41.000Z",
            tmdbId = "6662b013eba41395c5528e84",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        `when`(videoService.getById(1L)).thenReturn(expectedResponse)

        mockMvc.perform(
            get("/videos/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(
                content().json(objectMapper.writeValueAsString(expectedResponse))
            )
    }

    @Test
    @DisplayName("Devem retornar status 200 ao cadastrar um video")
    fun testCreate() {
        val request = """
        {
            "movieId": 1,
            "iso6391": "en",
            "iso31661": "US",
            "name": "Meet the Cast of Inside Out 2",
            "videoKey": "uyzQw8-8k9U",
            "site": "YouTube",
            "size": 1080,
            "type": "Trailer",
            "official": true,
            "publishedAt": "2024-06-07T00:37:41.000Z",
            "tmdbId": "6662b013eba41395c5528e84"
        }
        """.trimIndent()

        val expectedResponse = Video(
            id = 1L,
            movieId = 1,
            iso6391 = "en",
            iso31661 = "US",
            name = "Meet the Cast of Inside Out 2",
            videoKey = "uyzQw8-8k9U",
            site = "YouTube",
            size = 1080,
            type = "Trailer",
            official = true,
            publishedAt = "2024-06-07T00:37:41.000Z",
            tmdbId = "6662b013eba41395c5528e84",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        val requestObject = objectMapper.readValue(request, Video::class.java)
        `when`(videoService.save(requestObject)).thenReturn(expectedResponse)

        mockMvc.perform(
            post("/videos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        )
            .andExpect(status().isOk)
            .andExpect(
                content().json(objectMapper.writeValueAsString(expectedResponse))
            )

    }

    @Test
    @DisplayName("Devem retornar status 200 ao atualizar um video")
    fun testUpdate() {
        val request = """
        {
            "movieId": 1,
            "iso6391": "en",
            "iso31661": "US",
            "name": "Meet the Cast of Inside Out 2",
            "videoKey": "uyzQw8-8k9U",
            "site": "YouTube",
            "size": 1080,
            "type": "Trailer",
            "official": true,
            "publishedAt": "2024-06-07T00:37:41.000Z",
            "tmdbId": "6662b013eba41395c5528e84"
        }
        """.trimIndent()

        val expectedResponse = Video(
            id = 1L,
            movieId = 1,
            iso6391 = "en",
            iso31661 = "US",
            name = "Meet the Cast of Inside Out 2",
            videoKey = "uyzQw8-8k9U",
            site = "YouTube",
            size = 1080,
            type = "Trailer",
            official = true,
            publishedAt = "2024-06-07T00:37:41.000Z",
            tmdbId = "6662b013eba41395c5528e84",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        val requestObject = objectMapper.readValue(request, Video::class.java)
        `when`(videoService.update(1L, requestObject)).thenReturn(expectedResponse)

        mockMvc.perform(
            patch("/videos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        )
            .andExpect(status().isOk)
            .andExpect(
                content().json(objectMapper.writeValueAsString(expectedResponse))
            )
    }

    @Test
    @DisplayName("Devem retornar status 200 ao deletar um video")
    fun testDelete() {
        `when`(videoService.delete(1L)).thenReturn(true)

        mockMvc.perform(
            delete("/videos/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isNoContent)
    }

}