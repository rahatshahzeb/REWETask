package com.example.rewetask.ui.weatherdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rewetask.db.city.CityDao
import com.example.rewetask.db.weather.WeatherDao
import com.example.rewetask.model.LocalCityDetail
import com.example.rewetask.model.LocalWeatherDetail
import com.example.rewetask.repository.CityRepository
import com.example.rewetask.repository.WeatherRepository
import com.example.usecase.ConvertTo12HourHumanReadableFormatUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class WeatherDetailViewModel(
    cityName: String,
    weatherDao: WeatherDao,
    cityDao: CityDao,
    private val cityRepository: CityRepository = CityRepository(cityDao = cityDao),
    private val weatherRepository: WeatherRepository = WeatherRepository(weatherDao = weatherDao),
    private val convertTo12HourHumanReadableFormatUseCase: ConvertTo12HourHumanReadableFormatUseCase = ConvertTo12HourHumanReadableFormatUseCase(),
) : ViewModel() {

    private val _viewState = MutableStateFlow<ViewState?>(ViewState.Loading)
    val viewState: Flow<ViewState> = _viewState.filterNotNull()

    init {
        loadWeatherData(cityName)
    }

    fun loadWeatherData(cityName: String) {
        viewModelScope.launch {
            val remoteWeatherDetail = weatherRepository.fetchCityWeather(cityName)

            val weatherDetail = remoteWeatherDetail ?: weatherRepository.getCityWeather(cityName).firstOrNull()

            weatherDetail?.let {
                val updatedWeatherEntity = it.copy(
                    hourlyData = it.hourlyData.map { hour ->
                        hour.copy(time = convertTo12HourHumanReadableFormatUseCase(hour.time ?: ""))
                    }
                )
                _viewState.value = ViewState.Detail(updatedWeatherEntity)
            } ?: run { _viewState.value = ViewState.Empty }
        }
    }

    fun onUpdateBookmark(cityDetail: LocalCityDetail) {
        viewModelScope.launch {
            cityRepository.updateCity(
                cityDetail.copy(isBookmarked = !cityDetail.isBookmarked)
            )
        }
    }

    sealed class ViewState {
        data object Loading : ViewState()
        data object Empty : ViewState()
        data class Detail(val weatherDetail: LocalWeatherDetail) : ViewState()
    }
}