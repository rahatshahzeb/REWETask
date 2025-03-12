package com.example.rewetask.ui.weatherdetail

import app.cash.turbine.test
import com.example.rewetask.MainCoroutineExtension
import com.example.rewetask.db.city.CityDao
import com.example.rewetask.db.weather.WeatherDao
import com.example.rewetask.model.LocalWeatherDetail
import com.example.rewetask.repository.CityRepository
import com.example.rewetask.repository.WeatherRepository
import com.example.usecase.ConvertTo12HourHumanReadableFormatUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineExtension::class)
class WeatherDetailViewModelTest {

    private val cityDao: CityDao = mockk()
    private val weatherDao: WeatherDao = mockk()
    private val cityRepository: CityRepository = mockk()
    private val weatherRepository: WeatherRepository = mockk()
    private val convertTo12HourHumanReadableFormatUseCase: ConvertTo12HourHumanReadableFormatUseCase = ConvertTo12HourHumanReadableFormatUseCase()

    @Test
    fun `Given a valid city name When fetched Then weather data is returned`() = runTest{
        // Given
        coEvery { weatherRepository.fetchCityWeather(any()) } returns remoteWeatherDetail

        // When
        val viewModel: WeatherDetailViewModel = createViewModel("some_valid_input")

        // Then
        viewModel.viewState.test {
            assertEquals(WeatherDetailViewModel.ViewState.Detail(remoteWeatherDetail), awaitItem())
        }
    }

    @Test
    fun `Given a valid city name When error occurs Then empty weather data is returned`() = runTest{
        // Given
        coEvery { weatherRepository.fetchCityWeather(any()) } returns null
        coEvery { weatherRepository.getCityWeather(any()) } returns flowOf(null)

        // When
        val viewModel: WeatherDetailViewModel = createViewModel("some_valid_input")

        // Then
        viewModel.viewState.test {
            assertEquals(WeatherDetailViewModel.ViewState.Empty, awaitItem())
        }
    }

    private val remoteWeatherDetail = LocalWeatherDetail(
        cityName = "Vienna",
        countryName = "Austria",
        hourlyData = emptyList(),
        isBookmarked = false,

    )

    private fun createViewModel(cityName: String): WeatherDetailViewModel = WeatherDetailViewModel(
        cityName = cityName,
        cityDao = cityDao,
        weatherDao = weatherDao,
        cityRepository = cityRepository,
        weatherRepository = weatherRepository,
        convertTo12HourHumanReadableFormatUseCase = convertTo12HourHumanReadableFormatUseCase
    )
}