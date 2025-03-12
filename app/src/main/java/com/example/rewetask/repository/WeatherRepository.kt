package com.example.rewetask.repository

import com.example.rewetask.db.weather.WeatherDao
import com.example.rewetask.model.LocalWeatherDetail
import com.example.rewetask.network.RetrofitClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class WeatherRepository(
    private val weatherDao: WeatherDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

    suspend fun fetchCityWeather(cityName: String): LocalWeatherDetail? = withContext(ioDispatcher) {
        val weatherResponse = runCatching {
            RetrofitClient.weatherApiService.cityWeather(
                cityName = cityName,
            ).execute().body()
        }.getOrNull()

        weatherResponse?.let {
            it.toDb()?.let { weatherEntity ->
                weatherDao.insertOrReplace(weatherEntity)
            }
        }

        return@withContext weatherResponse?.toDomain()
    }

    suspend fun getCityWeather(cityName: String): Flow<LocalWeatherDetail> = withContext(ioDispatcher) {
        return@withContext weatherDao.findWeatherByCityName(cityName).map { weatherEntity ->
            weatherEntity.toDomain()
        }
    }

    suspend fun insertOrReplace(weatherDetail: LocalWeatherDetail) = withContext(ioDispatcher) {
        weatherDao.insertOrReplace(weatherDetail.toDb())
    }
}