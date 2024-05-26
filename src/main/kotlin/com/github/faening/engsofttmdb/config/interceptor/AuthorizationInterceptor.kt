package com.github.faening.engsofttmdb.config.interceptor

import okhttp3.Interceptor

class AuthorizationInterceptor : Interceptor {

    private val tmdbApiToken = "ADD_TOKEN_HERE"

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .header("Authorization", "Bearer $tmdbApiToken")
            .method(original.method, original.body)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}