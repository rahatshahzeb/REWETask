package com.example.rewetask.network

import com.example.rewetask.db.city.CityResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CityApiService {
    @GET("searchJSON")
    fun searchCity(
        @Query("q") query: String,
        @Query("maxRows") maxRows: Int = 10,
        @Query("username") username: String = "shahzebrahat"
    ): Call<CityResponse>
}