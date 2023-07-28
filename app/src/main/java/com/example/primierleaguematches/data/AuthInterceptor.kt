package com.example.primierleaguematches.data

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor(private val authToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()
            .header("X-Auth-Token", authToken)
        return chain.proceed(requestBuilder.build())
    }
}