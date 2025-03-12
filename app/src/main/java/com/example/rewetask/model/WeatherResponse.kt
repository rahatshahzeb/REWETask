package com.example.rewetask.model

import android.util.Log
import com.example.rewetask.db.weather.WeatherEntity
import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("current_condition") var currentCondition: ArrayList<CurrentCondition> = arrayListOf(),
    @SerializedName("nearest_area") var nearestArea: ArrayList<NearestArea> = arrayListOf(),
    @SerializedName("request") var request: ArrayList<Request> = arrayListOf(),
    @SerializedName("weather") var weather: ArrayList<Weather> = arrayListOf()
) {
    fun toDb(): WeatherEntity? {
        try {
            return WeatherEntity(
                cityName = nearestArea[0].areaName[0].value ?: "",
                countryName = nearestArea[0].country[0].value ?: "",
                tempC = currentCondition[0].tempC,
                feelsLikeC = currentCondition[0].feelsLikeC,
                uvIndex = currentCondition[0].uvIndex,
                pressure = currentCondition[0].pressure,
                weatherDesc = currentCondition[0].weatherDesc[0].value,
                windSpeedKmph = currentCondition[0].windSpeedKmph,
                humidity = currentCondition[0].humidity,
                hourlyData = weather[0].hourly,
                )
        } catch (e: ArrayIndexOutOfBoundsException) {
            Log.e("WeatherResponse", e.message.toString())
            return null
        } catch (e: Exception) {
            Log.e("WeatherResponse", e.message.toString())
            return null
        }
    }

    fun toDomain(): LocalWeatherDetail? {
        try {
            return LocalWeatherDetail(
                cityName = nearestArea[0].areaName[0].value ?: "",
                countryName = nearestArea[0].country[0].value ?: "",
                tempC = currentCondition[0].tempC,
                feelsLikeC = currentCondition[0].feelsLikeC,
                uvIndex = currentCondition[0].uvIndex,
                pressure = currentCondition[0].pressure,
                weatherDesc = currentCondition[0].weatherDesc[0].value,
                windSpeedKmph = currentCondition[0].windSpeedKmph,
                humidity = currentCondition[0].humidity,
                sunRise = weather[0].astronomy[0].sunRise,
                sunSet = weather[0].astronomy[0].sunSet,
                hourlyData = weather[0].hourly,
            )
        } catch (e: ArrayIndexOutOfBoundsException) {
            Log.e("WeatherResponse", e.message.toString())
            return null
        } catch (e: Exception) {
            Log.e("WeatherResponse", e.message.toString())
            return null
        }
    }
}
