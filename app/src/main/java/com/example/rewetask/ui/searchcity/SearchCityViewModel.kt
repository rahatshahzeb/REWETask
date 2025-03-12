package com.example.rewetask.ui.searchcity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rewetask.db.city.CityDao
import com.example.rewetask.model.LocalCityDetail
import com.example.rewetask.repository.CityRepository
import com.example.usecase.SortCityListByBookmarkAndMergeUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class SearchCityViewModel(
    cityDao: CityDao,
    private val cityRepository: CityRepository = CityRepository(cityDao = cityDao),
    private val sortCityListByBookmarksAndMerge: SortCityListByBookmarkAndMergeUseCase = SortCityListByBookmarkAndMergeUseCase(),
) : ViewModel() {
    private val _viewState = MutableStateFlow<CityListViewState?>(CityListViewState.Loading)
    val viewState: Flow<CityListViewState> = _viewState.filterNotNull()

    private var remoteCities: List<LocalCityDetail> = emptyList()

    init {
        viewModelScope.launch {
            cityRepository.getCities().collect { bookmarkedCities ->
                val distinctCities =
                    sortCityListByBookmarksAndMerge(
                        remoteCities = remoteCities,
                        bookmarkedCities = bookmarkedCities,
                    )

                _viewState.value =
                    if (distinctCities.isEmpty())
                        CityListViewState.Empty
                    else CityListViewState.CityList(distinctCities)
            }
        }
    }

    fun onSearch(searchQuery: String) {
        viewModelScope.launch {
            remoteCities = cityRepository.searchCity(searchQuery) ?: emptyList()
            val bookmarkedCities =
                cityRepository.getCities().firstOrNull() ?: emptyList()
            val distinctCities = sortCityListByBookmarksAndMerge(
                remoteCities = remoteCities,
                bookmarkedCities = bookmarkedCities,
            )

            _viewState.value =
                if (distinctCities.isEmpty()) CityListViewState.Empty else CityListViewState.CityList(distinctCities)

        }
    }

    fun onUpdateBookmark(cityDetail: LocalCityDetail) {
        viewModelScope.launch {
            cityRepository.updateCity(
                cityDetail.copy(isBookmarked = !cityDetail.isBookmarked)
            )
        }
    }
}
