package com.example.rewetask.ui.searchcity

import app.cash.turbine.test
import com.example.rewetask.MainCoroutineExtension
import com.example.rewetask.db.city.CityDao
import com.example.rewetask.model.LocalCityDetail
import com.example.rewetask.repository.CityRepository
import com.example.usecase.SortCityListByBookmarkAndMergeUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineExtension::class)
class SearchCityViewModelTest {

    private val cityDao: CityDao = mockk()
    private val cityRepository: CityRepository = mockk()
    private val sortCityListByBookmarkAndMergeUseCase: SortCityListByBookmarkAndMergeUseCase = SortCityListByBookmarkAndMergeUseCase()

    @Test
    fun `Given no favorite cities When invalid city name Then no city is returned`() = runTest{
        // Given
        coEvery { cityRepository.getCities() } returns emptyFlow()
        coEvery { cityRepository.searchCity(any()) } returns emptyList()

        val viewModel: SearchCityViewModel = createViewModel()

        // When
        viewModel.onSearch("some_invalid_input")

        // Then
        viewModel.viewState.test {
            assertEquals(CityListViewState.Empty, awaitItem())
        }
    }

    @Test
    fun `Given no favorite cities When valid city name Then remote cities are returned`() = runTest{
        // Given
        coEvery { cityRepository.getCities() } returns emptyFlow()
        coEvery { cityRepository.searchCity(any()) } returns remoteCities

        val viewModel: SearchCityViewModel = createViewModel()

        // When
        viewModel.onSearch("some_valid_input")

        // Then
        viewModel.viewState.test {
            assertEquals(CityListViewState.CityList(remoteCities), awaitItem())
        }
    }

    @Test
    fun `Given favorite cities When valid city name Then combined cities are returned`() = runTest{
        // Given
        coEvery { cityRepository.getCities() } returns flowOf(bookmarkedCities)
        coEvery { cityRepository.searchCity(any()) } returns remoteCities

        val viewModel: SearchCityViewModel = createViewModel()

        val combinedCities = sortCityListByBookmarkAndMergeUseCase(
            remoteCities = remoteCities,
            bookmarkedCities = bookmarkedCities,
        )

        // When
        viewModel.onSearch("some_valid_input")

        // Then
        viewModel.viewState.test {
            assertEquals(CityListViewState.CityList(combinedCities), awaitItem())
        }
    }

    private fun createViewModel(): SearchCityViewModel = SearchCityViewModel(
        cityDao = cityDao,
        cityRepository = cityRepository,
        sortCityListByBookmarksAndMerge = sortCityListByBookmarkAndMergeUseCase
    )

    private val remoteCities = listOf(
        LocalCityDetail(
            name = "London",
            isBookmarked = false,
        ),
        LocalCityDetail(
            name = "Paris",
            isBookmarked = false,
        ),
        LocalCityDetail(
            name = "New York",
            isBookmarked = false,
        ),
    )

    private val bookmarkedCities = listOf(
        LocalCityDetail(
            name = "Vienna",
            isBookmarked = true,
        ),
        LocalCityDetail(
            name = "Paris",
            isBookmarked = true,
        ),
    )
}