package com.example.rewetask.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
data class WeatherDetail(
    val cityName: String = "",
    val countryName: String = "",
    val isBookmarked: Boolean = false,
)
