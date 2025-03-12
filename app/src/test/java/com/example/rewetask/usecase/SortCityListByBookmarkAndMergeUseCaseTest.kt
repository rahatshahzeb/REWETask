package com.example.rewetask.usecase

import com.example.rewetask.model.LocalCityDetail
import com.example.usecase.SortCityListByBookmarkAndMergeUseCase
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SortCityListByBookmarkAndMergeUseCaseTest {

    private val sortCityListByBookmarkAndMergeUseCase: SortCityListByBookmarkAndMergeUseCase = SortCityListByBookmarkAndMergeUseCase()

    @Test
    fun verify_remote_cities_are_sorted_by_bookmark_and_merged() = runTest {
        // Given
        val remoteCities = listOf(
            LocalCityDetail(name = "City1", countryName = "Country1", isBookmarked = true),
            LocalCityDetail(name = "City2", countryName = "Country2", isBookmarked = false),
            LocalCityDetail(name = "City3", countryName = "Country3", isBookmarked = false),
            LocalCityDetail(name = "City4", countryName = "Country4", isBookmarked = true),
            LocalCityDetail(name = "City5", countryName = "Country5", isBookmarked = false),
        )

        val bookmarkedCities = listOf(
            LocalCityDetail(name = "City3", countryName = "Country3", isBookmarked = true),
            LocalCityDetail(name = "City4", countryName = "Country4", isBookmarked = true),
        )

        // When
        val result = sortCityListByBookmarkAndMergeUseCase(remoteCities, bookmarkedCities)

        // Then
        assertEquals(5, result.size)
        assertEquals(result[0], bookmarkedCities[0])
        assertEquals(result[1], bookmarkedCities[1])
        assertEquals(result[2], remoteCities[0])

    }
}