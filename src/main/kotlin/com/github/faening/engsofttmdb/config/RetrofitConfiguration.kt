package com.github.faening.engsofttmdb.config

import LanguageInterceptor
import com.github.faening.engsofttmdb.config.interceptor.AuthorizationInterceptor
import com.github.faening.engsofttmdb.data.service.TmdbService
import okhttp3.OkHttpClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Suppress("unused")
@Configuration
class RetrofitConfiguration {

    private val tmdbApiUrl = "https://api.themoviedb.org/3/"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthorizationInterceptor())
        .addInterceptor(LanguageInterceptor())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(tmdbApiUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Bean
    fun tmdbService(): TmdbService {
        return retrofit.create(TmdbService::class.java)
    }

}