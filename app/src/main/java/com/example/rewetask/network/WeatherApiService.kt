package com.example.rewetask.network

import com.example.rewetask.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApiService {
    @GET("/{city}")
    fun cityWeather(
        @Path("city") cityName: String,
        @Query("format") format: String = "j1"
    ): Call<WeatherResponse>
}