package com.example.rewetask.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val CITY_SEARCH_BASE_URL = "http://api.geonames.org/"
    private const val WEATHER_BASE_URL = "https://wttr.in/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    fun getRetrofitInstance(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val cityApiService: CityApiService by lazy {
        getRetrofitInstance(CITY_SEARCH_BASE_URL).create(CityApiService::class.java)
    }

    val weatherApiService: WeatherApiService by lazy {
        getRetrofitInstance(WEATHER_BASE_URL).create(WeatherApiService::class.java)
    }
}
