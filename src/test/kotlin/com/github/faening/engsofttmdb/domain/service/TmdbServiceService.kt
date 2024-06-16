package com.github.faening.engsofttmdb.domain.service

import com.github.faening.engsofttmdb.data.model.api.ResponsePageApiData
import com.github.faening.engsofttmdb.data.model.api.credits.CreditsApiData
import com.github.faening.engsofttmdb.data.model.api.genres.GenresPageApiData
import com.github.faening.engsofttmdb.data.model.api.movie.MovieApiData
import com.github.faening.engsofttmdb.data.model.api.reviews.AuthorDetailsApiData
import com.github.faening.engsofttmdb.data.model.api.reviews.ReviewApiData
import com.github.faening.engsofttmdb.data.model.api.videos.VideoApiData
import com.github.faening.engsofttmdb.data.model.api.videos.VideosPageApiData
import com.github.faening.engsofttmdb.data.service.TmdbApi
import com.github.faening.engsofttmdb.domain.mapper.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.springframework.boot.test.context.SpringBootTest
import retrofit2.Call
import retrofit2.Response

@SpringBootTest(properties = ["spring.profiles.active=test"])
class TmdbServiceService {

    private lateinit var tmdbApi: TmdbApi
    private lateinit var service: TmdbService
    private lateinit var genreService: GenreService
    private lateinit var genreMapper: GenreMapper
    private lateinit var movieService: MovieService
    private lateinit var movieMapper: MovieMapper
    private lateinit var castService: CastService
    private lateinit var castMapper: CastMapper
    private lateinit var crewService: CrewService
    private lateinit var crewMapper: CrewMapper
    private lateinit var authorDetailsService: AuthorDetailsService
    private lateinit var authorDetailsMapper: AuthorDetailsMapper
    private lateinit var reviewService: ReviewService
    private lateinit var reviewMapper: ReviewMapper
    private lateinit var videoService: VideoService
    private lateinit var videoMapper: VideoMapper

    @BeforeEach
    fun setUp() {
        tmdbApi = mock(TmdbApi::class.java)
        genreService = mock(GenreService::class.java)
        genreMapper = mock(GenreMapper::class.java)
        movieService = mock(MovieService::class.java)
        movieMapper = mock(MovieMapper::class.java)
        castService = mock(CastService::class.java)
        castMapper = mock(CastMapper::class.java)
        crewService = mock(CrewService::class.java)
        crewMapper = mock(CrewMapper::class.java)
        authorDetailsService = mock(AuthorDetailsService::class.java)
        authorDetailsMapper = mock(AuthorDetailsMapper::class.java)
        reviewService = mock(ReviewService::class.java)
        reviewMapper = mock(ReviewMapper::class.java)
        videoService = mock(VideoService::class.java)
        videoMapper = mock(VideoMapper::class.java)

        service = TmdbService(
            tmdbApi,
            genreService,
            genreMapper,
            movieService,
            movieMapper,
            castService,
            castMapper,
            crewService,
            crewMapper,
            authorDetailsService,
            authorDetailsMapper,
            reviewService,
            reviewMapper,
            videoService,
            videoMapper
        )
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    @DisplayName("Deve retornar GenresPageApiData quando chamado")
    fun getAllGenres_ShouldReturnGenresPageApiData_WhenCalled() {
        val expectedGenres = GenresPageApiData(emptyList())
        val callMock = mock(Call::class.java) as Call<GenresPageApiData>
        val responseMock = mock(Response::class.java) as Response<GenresPageApiData>

        `when`(tmdbApi.getAllGenres()).thenReturn(callMock)
        `when`(callMock.execute()).thenReturn(responseMock)
        `when`(responseMock.isSuccessful).thenReturn(true)
        `when`(responseMock.body()).thenReturn(expectedGenres)

        val result = service.getAllGenres()

        assertEquals(expectedGenres, result)
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    @DisplayName("Deve chamar saveAllEntities do genreService com genresMappedToEntity")
    fun getAllMovies_ShouldReturnListOfMovieApiData_WhenCalled() {
        val expectedMovies = ResponsePageApiData(
            id = 1,
            page = 1,
            results = listOf(
                MovieApiData(
                    adult = false,
                    backdropPath = "/fqv8v6AycXKsivp1T5yKtLbGXce.jpg",
                    genreIds = listOf(17, 25),
                    id = 653346,
                    originalLanguage = "en",
                    originalTitle = "movie_1",
                    overview = "overview_1",
                    popularity = 2.0,
                    posterPath = "/dzDK2TMXsxrolGVdZwNGcOlZqrF.jpg",
                    releaseDate = "2024-05-08",
                    title = "movie_1",
                    video = false,
                    voteAverage = 6.822,
                    voteCount = 1040
                )
            ),
            totalPages = 1,
            totalResults = 1
        )
        val callMock = mock(Call::class.java) as Call<ResponsePageApiData<MovieApiData>>
        val responseMock = mock(Response::class.java) as Response<ResponsePageApiData<MovieApiData>>

        `when`(tmdbApi.getAllMovies()).thenReturn(callMock)
        `when`(callMock.execute()).thenReturn(responseMock)
        `when`(responseMock.isSuccessful).thenReturn(true)
        `when`(responseMock.body()).thenReturn(expectedMovies)

        val result = service.getAllMovies()

        assertEquals(expectedMovies.results, result)
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    @DisplayName("Deve retornar AuthenticationApiData quando chamado")
    fun getMovieCredits_ShouldReturnCreditsApiData_WhenCalled() {
        val tmdbId = 1L
        val expectedCredits = CreditsApiData(movieId = 0, cast = emptyList(), crew = emptyList())
        val callMock = mock(Call::class.java) as Call<CreditsApiData>
        val responseMock = mock(Response::class.java) as Response<CreditsApiData>

        `when`(tmdbApi.getMovieCredits(tmdbId)).thenReturn(callMock)
        `when`(callMock.execute()).thenReturn(responseMock)
        `when`(responseMock.isSuccessful).thenReturn(true)
        `when`(responseMock.body()).thenReturn(expectedCredits)

        val result = service.getMovieCredits(tmdbId)

        assertEquals(expectedCredits, result)
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    @DisplayName("Deve retornar lista de ReviewApiData quando chamado")
    fun getMovieReviews_ShouldReturnListOfReviewApiData_WhenCalled() {
        val movieId = 1L
        val expectedReviews = ResponsePageApiData(
            id = 1,
            page = 1,
            results = listOf(
                ReviewApiData(
                    author = "author_1",
                    authorDetails = AuthorDetailsApiData(
                        name = "name_1",
                        username = "username_1",
                        avatarPath = "avatarPath_1",
                        rating = 8
                    ),
                    content = "content_1",
                    tmdbId = "1",
                    url = "https://www.themoviedb.org/review/6669f31a282af652f2d05392",
                    createdAt = "2024-06-15T21:25:08",
                    updatedAt = "2024-06-15T21:25:08",
                )
            ),
            totalPages = 1,
            totalResults = 1
        )

        val callMock = mock(Call::class.java) as Call<ResponsePageApiData<ReviewApiData>>
        val responseMock = mock(Response::class.java) as Response<ResponsePageApiData<ReviewApiData>>

        `when`(tmdbApi.getMovieReviews(movieId)).thenReturn(callMock)
        `when`(callMock.execute()).thenReturn(responseMock)
        `when`(responseMock.isSuccessful).thenReturn(true)
        `when`(responseMock.body()).thenReturn(expectedReviews)

        val result = service.getMovieReviews(movieId)

        assertEquals(expectedReviews.results, result)
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    @DisplayName("Deve retornar lista de VideoApiData quando chamado")
    fun getMovieVideos_ShouldReturnListOfVideoApiData_WhenCalled() {
        val movieId = 1L
        val expectedVideos = VideosPageApiData(
            id = 1,
            results = listOf(
                VideoApiData(
                    iso6391 = "en",
                    iso31661 = "US",
                    key = "uskbXdnbcvg",
                    name = "name_1",
                    site = "YouTube",
                    size = 1080,
                    type = "Trailer",
                    official = true,
                    publishedAt = "2024-06-15T21:25:08",
                    tmdbId = "1"
                )
            )
        )
        val callMock = mock(Call::class.java) as Call<VideosPageApiData>
        val responseMock = mock(Response::class.java) as Response<VideosPageApiData>

        `when`(tmdbApi.getMovieVideos(movieId)).thenReturn(callMock)
        `when`(callMock.execute()).thenReturn(responseMock)
        `when`(responseMock.isSuccessful).thenReturn(true)
        `when`(responseMock.body()).thenReturn(expectedVideos)

        val result = service.getMovieVideos(movieId)

        assertEquals(expectedVideos.results, result)
    }

}