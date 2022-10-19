package com.example.shutter.data.remote.api

import com.example.shutter.constants.token
import com.example.shutter.data.entity.Pics
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIService {


    @GET("images/search")
    suspend fun search(
        @Query("query") query: String,
        @Query("page") page: Int,
    ) : Pics

    companion object {
        const val BASE_URL = "https://api.shutterstock.com/v2/"
    }
}
