package com.example.rewetask.ui.searchcity

import com.example.rewetask.model.LocalCityDetail

sealed class CityListViewState {
    data object Loading : CityListViewState()
    data object Empty : CityListViewState()
    data class CityList(val cities: List<LocalCityDetail>) : CityListViewState()
}
