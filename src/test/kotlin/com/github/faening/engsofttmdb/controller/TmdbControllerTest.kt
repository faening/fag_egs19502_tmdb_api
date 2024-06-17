package com.github.faening.engsofttmdb.controller

import com.github.faening.engsofttmdb.data.model.api.authentication.AuthenticationApiData
import com.github.faening.engsofttmdb.domain.service.TmdbService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TmdbControllerTest {

    @Mock
    private lateinit var tmdbService: TmdbService

    @InjectMocks
    private lateinit var tmdbController: TmdbController

    @MockBean
    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(tmdbController).build()
    }

    @Test
    @DisplayName("Deve retornar 200 quando a autenticação for bem sucedida")
    fun testAuthentication() {
        val expectedResponse = AuthenticationApiData(
            success = true,
            statusCode = 200,
            statusMessage = "Autenticação bem sucedida"
        )
        `when`(tmdbService.authentication()).thenReturn(expectedResponse)

        mockMvc.perform(
            get("/tmdb/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
    }

    @Test
    @DisplayName("Deve retornar 200 quando o serviço fetchDataAndPopulateLocalDatabase for bem sucedido")
    fun testPopulate() {
        mockMvc.perform(
            get("/tmdb/populate")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
    }

}