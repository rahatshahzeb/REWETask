package com.example.usecase

import com.example.rewetask.model.LocalCityDetail
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SortCityListByBookmarkAndMergeUseCase(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

    suspend operator fun invoke(remoteCities: List<LocalCityDetail>, bookmarkedCities: List<LocalCityDetail>): List<LocalCityDetail> = withContext(ioDispatcher) {
        // Use (name, countryName) as key for uniqueness
        val cityMap = LinkedHashMap<Pair<String, String>, LocalCityDetail>()

        // Add bookmarked cities first to ensure they appear at the top
        bookmarkedCities.forEach { city ->
            // Ensure they are marked as bookmarked
            cityMap[city.name to city.countryName] = city.copy(isBookmarked = true)
        }

        // Add non-bookmarked cities if they are not already present
        remoteCities.forEach { city ->
            cityMap.putIfAbsent(city.name to city.countryName, city)
        }
        return@withContext cityMap.values.toList()

    }
}