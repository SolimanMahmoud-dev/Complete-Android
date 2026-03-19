package com.example.bookly.network

import com.example.bookly.models.api.ApiResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("volumes")
    suspend fun getBooks(
        @Query("q") query: String,
        @Query("Filtering") filtering: String
    ): ApiResponse

    @GET("volumes")
     suspend fun getNewestBooks(
        @Query("q") query: String,
        @Query("Filtering") filtering: String,
        @Query("Sorting") sorting: String
    ): ApiResponse
}