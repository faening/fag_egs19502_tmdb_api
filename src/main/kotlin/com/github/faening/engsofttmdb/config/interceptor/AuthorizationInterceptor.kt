package com.github.faening.engsofttmdb.config.interceptor

import okhttp3.Interceptor

class AuthorizationInterceptor : Interceptor {

    private val tmdbApiToken = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1NTg0MzU2MWJiNDA4MTcxOTg4ODU5Y2ZiOGU4MGJhMSIsInN1YiI6IjY1YjI2Yjc3MWM2MzI5MDEzOTkzMDE5MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.W5JpW2TbmoZH9Il503OJDjxal3og9ivVW6mn5HK8VzQ"

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .header("Authorization", "Bearer $tmdbApiToken")
            .method(original.method, original.body)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}