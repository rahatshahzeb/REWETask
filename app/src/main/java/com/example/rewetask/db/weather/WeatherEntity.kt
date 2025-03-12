package com.example.rewetask.db.weather

import androidx.room.Entity
import androidx.room.TypeConverters
import com.example.rewetask.model.Hourly
import com.example.rewetask.model.LocalWeatherDetail

@Entity(primaryKeys = ["cityName"])
@TypeConverters(HourlyListConverter::class)
data class WeatherEntity(
    val cityName: String = "",
    val countryName: String = "",
    val isBookmarked: Boolean = false,
    val tempC: String? = null,
    val feelsLikeC: String? = null,
    val uvIndex: String? = null,
    val pressure: String? = null,
    val weatherDesc: String? = null,
    val windSpeedKmph: String? = null,
    val humidity: String? = null,
    val sunRise: String? = null,
    val sunSet: String? = null,
    val hourlyData: List<Hourly> = emptyList()
) {
    fun toDomain(): LocalWeatherDetail = LocalWeatherDetail(
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
        sunRise = this.sunRise,
        sunSet = this.sunSet,
        hourlyData = this.hourlyData,
    )
}