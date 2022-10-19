package com.example.shutter.di

import com.example.shutter.constants.token
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor  : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        return chain.proceed(request)
    }
}