package com.github.faening.engsofttmdb.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.faening.engsofttmdb.domain.model.*
import com.github.faening.engsofttmdb.domain.service.MovieService
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
import java.time.LocalDate
import java.time.LocalDateTime

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class MovieControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private var objectMapper: ObjectMapper
) {

    @MockBean
    private lateinit var movieService: MovieService

    @Test
    @DisplayName("Devem retornar status 200 ao buscar todos os filmes")
    fun testGetAll() {
        mockMvc.perform(
            get("/movies")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
    }

    @Test
    @DisplayName("Devem retornar status 200 ao buscar um filme por ID")
    fun testGetById() {
        val expectedResponse = Movie(
            id = 1L,
            adult = false,
            backdropPath = "/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
            genreIds = listOf(16, 35, 10751),
            tmdbId = 150540,
            originalLanguage = "en",
            originalTitle = "Inside Out 2",
            overview = "Riley's 15 now...",
            popularity = 26.804,
            posterPath = "/rwmvRonpluV6dCPiQissYrchvSD.jpg",
            releaseDate = LocalDate.parse("2024-06-07"),
            title = "Inside Out 2",
            video = false,
            voteAverage = 7.8,
            voteCount = 1234,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        `when`(movieService.getById(1L)).thenReturn(expectedResponse)

        mockMvc.perform(
            get("/movies/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(
                content().json(objectMapper.writeValueAsString(expectedResponse))
            )
    }

    @Test
    @DisplayName("Devem retornar status 200 ao cadastrar um filme")
    fun testCreate() {
        val request = """
        {
            "adult": false,
            "backdropPath": "/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
            "genreIds": [16, 35, 10751],
            "tmdbId": 150540,
            "originalLanguage": "en",
            "originalTitle": "Inside Out 2",
            "overview": "Riley's 15 now...",
            "popularity": 26.804,
            "posterPath": "/rwmvRonpluV6dCPiQissYrchvSD.jpg",
            "releaseDate": "2024-06-07",
            "title": "Inside Out 2",
            "video": false,
            "voteAverage": 7.8,
            "voteCount": 1234
        }
        """.trimIndent()

        val expectedResponse = Movie(
            id = 1L,
            adult = false,
            backdropPath = "/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
            genreIds = listOf(16, 35, 10751),
            tmdbId = 150540,
            originalLanguage = "en",
            originalTitle = "Inside Out 2",
            overview = "Riley's 15 now...",
            popularity = 26.804,
            posterPath = "/rwmvRonpluV6dCPiQissYrchvSD.jpg",
            releaseDate = LocalDate.parse("2024-06-07"),
            title = "Inside Out 2",
            video = false,
            voteAverage = 7.8,
            voteCount = 1234,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        val requestObject = objectMapper.readValue(request, Movie::class.java)
        `when`(movieService.save(requestObject)).thenReturn(expectedResponse)

        mockMvc.perform(
            post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        )
            .andExpect(status().isOk)
            .andExpect(
                content().json(objectMapper.writeValueAsString(expectedResponse))
            )
    }

    @Test
    @DisplayName("Devem retornar status 200 ao atualizar um filme")
    fun testUpdate() {
        val request = """
        {
            "adult": false,
            "backdropPath": "/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
            "genreIds": [16, 35, 10751],
            "tmdbId": 150540,
            "originalLanguage": "en",
            "originalTitle": "Inside Out 2",
            "overview": "Riley's 15 now...",
            "popularity": 26.804,
            "posterPath": "/rwmvRonpluV6dCPiQissYrchvSD.jpg",
            "releaseDate": "2024-06-07",
            "title": "Inside Out 2",
            "video": false,
            "voteAverage": 7.8,
            "voteCount": 1234
        }
        """.trimIndent()

        val expectedResponse = Movie(
            id = 1L,
            adult = false,
            backdropPath = "/8Y43POKjjKDGI9MH89NW0NAzzp8.jpg",
            genreIds = listOf(16, 35, 10751),
            tmdbId = 150540,
            originalLanguage = "en",
            originalTitle = "Inside Out 2",
            overview = "Riley's 15 now...",
            popularity = 26.804,
            posterPath = "/rwmvRonpluV6dCPiQissYrchvSD.jpg",
            releaseDate = LocalDate.parse("2024-06-07"),
            title = "Inside Out 2",
            video = false,
            voteAverage = 7.8,
            voteCount = 1234,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        val requestObject = objectMapper.readValue(request, Movie::class.java)
        `when`(movieService.update(1L, requestObject)).thenReturn(expectedResponse)

        mockMvc.perform(
            patch("/movies/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        )
            .andExpect(status().isOk)
            .andExpect(
                content().json(objectMapper.writeValueAsString(expectedResponse))
            )
    }

    @Test
    @DisplayName("Devem retornar status 200 ao deletar um filme")
    fun testDelete() {
        `when`(movieService.delete(1L)).thenReturn(true)

        mockMvc.perform(
            delete("/movies/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isNoContent)
    }

    @Test
    @DisplayName("Devem retornar status 200 ao buscar o elenco de um filme")
    fun testGetMovieCasts() {
        val movieId = 1L
        val expectedResponse = listOf(
            Cast(
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
                createdAt = LocalDateTime.parse("2024-06-15T21:25:07"),
                updatedAt = LocalDateTime.parse("2024-06-15T21:25:07")
            )
        )
        `when`(movieService.getMovieCasts(movieId)).thenReturn(expectedResponse)

        mockMvc.perform(
            get("/movies/${movieId}/casts")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)))
    }

    @Test
    @DisplayName("Devem retornar status 200 ao buscar a equipe de um filme")
    fun testGetMovieCrews() {
        val movieId = 1L
        val expectedResponse = listOf(
            Crew(
                id = 24L,
                adult = false,
                gender = 1,
                tmdbId = 963497,
                knownForDepartment = "Production",
                name = "Natalie Lyon",
                originalName = "Natalie Lyon",
                popularity = 4.066,
                profilePath = null,
                creditId = "6652b854a39cf9b9b7c62be4",
                department = "Production",
                job = "Casting",
                createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
                updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
            )
        )
        `when`(movieService.getMovieCrews(movieId)).thenReturn(expectedResponse)

        mockMvc.perform(
            get("/movies/${movieId}/crews")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)))
    }

    @Test
    @DisplayName("Devem retornar status 200 ao buscar as avaliações de um filme")
    fun testGetMovieReviews() {
        val movieId = 1L
        val expectedResponse = listOf(
            Review(
                id = 1L,
                movieId = 1L,
                author = "Hotplix",
                authorDetails = AuthorDetails(
                    id = 1L,
                    name = "Hotplix",
                    username = "Hotplix",
                    avatarPath = "/5LdGr01PGRmrg6Hh3LYPGlOOdUx.jpg",
                    rating = 8,
                    createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
                    updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
                ),
                content = "Content here...",
                tmdbId = "666193da58a1f88965b77e41",
                url = "https://www.themoviedb.org/review/666193da58a1f88965b77e41",
                createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
                updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
            )
        )
        `when`(movieService.getMovieReviews(movieId)).thenReturn(expectedResponse)

        mockMvc.perform(
            get("/movies/${movieId}/reviews")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)))
    }

    @Test
    @DisplayName("Devem retornar status 200 ao buscar os vídeos de um filme")
    fun testGetMovieVideos() {
        val movieId = 1L
        val expectedResponse = listOf(
            Video(
                id = 1L,
                movieId = 1L,
                iso6391 = "en",
                iso31661 = "US",
                name = "Meet the Cast of Inside Out 2",
                videoKey = "uyzQw8-8k9U",
                site = "YouTube",
                size = 1080,
                type = "Behind the Scenes",
                official = true,
                publishedAt ="2024-06-07T00:37:41",
                tmdbId = "6662b013eba41395c5528e84",
                createdAt = LocalDateTime.parse("2024-06-15T21:25:08"),
                updatedAt = LocalDateTime.parse("2024-06-15T21:25:08")
            )
        )
        `when`(movieService.getMovieVideos(movieId)).thenReturn(expectedResponse)

        mockMvc.perform(
            get("/movies/${movieId}/videos")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)))
    }

}