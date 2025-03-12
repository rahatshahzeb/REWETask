package com.example.rewetask.model

import com.example.rewetask.db.weather.WeatherEntity

data class LocalWeatherDetail (
    val cityName: String,
    val countryName: String,
    val isBookmarked: Boolean = false,
    val tempC: String? = null,
    val feelsLikeC: String? = null,
    val uvIndex: String? = null,
    val pressure: String? = null,
    val weatherDesc: String? = null,
    val windSpeedKmph: String? = null,
    val humidity: String? = null,
    val hourlyData: List<Hourly> = emptyList()
) {
    fun toDb(): WeatherEntity = WeatherEntity(
        cityName = this.cityName,
        countryName = this.countryName,
        isBookmarked =  this.isBookmarked,
        tempC = this.tempC,
        feelsLikeC = this.feelsLikeC,
        uvIndex = this.uvIndex,
        pressure = this.pressure,
        weatherDesc = this.weatherDesc,
        windSpeedKmph = this.windSpeedKmph,
        humidity = this.humidity,
        hourlyData = this.hourlyData,
    )

}