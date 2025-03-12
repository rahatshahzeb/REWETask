package com.example.rewetask.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.rewetask.db.AppDatabase
import com.example.rewetask.db.weather.WeatherDao
import com.example.rewetask.model.LocalWeatherDetail
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class WeatherRepositoryTest {

    private lateinit var weatherDao: WeatherDao
    private lateinit var db: AppDatabase
    private lateinit var repository: WeatherRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        )
            .setTransactionExecutor(testDispatcher.asExecutor())
            .setQueryExecutor(testDispatcher.asExecutor())
            .build()
        weatherDao = db.weatherDao()
        repository = WeatherRepository(weatherDao, testDispatcher)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun verify_weather_insertion() = runTest {
        // Given
        val weatherDetail = LocalWeatherDetail(
            cityName = "Vienna",
            countryName = "Austria",
            tempC = "15",
            feelsLikeC = "10",
            uvIndex = "10",
            pressure = "100",
            weatherDesc = "10",
            windSpeedKmph = "10",
            humidity = "10",
            hourlyData = emptyList()
        )

        // When
        repository.insertOrReplace(weatherDetail)

        // Then
        assertEquals(
            weatherDetail,
            repository.getCityWeather("Vienna").first()
        )
    }
}