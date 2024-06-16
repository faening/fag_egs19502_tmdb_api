package com.github.faening.engsofttmdb.config.interceptor

import io.github.cdimascio.dotenv.Dotenv
import okhttp3.Interceptor

class AuthorizationInterceptor : Interceptor {

    private val dotenv: Dotenv = Dotenv.load()
    private val tmdbApiToken = dotenv.get("TMDB_API_TOKEN")

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .header("Authorization", "Bearer $tmdbApiToken")
            .method(original.method, original.body)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}