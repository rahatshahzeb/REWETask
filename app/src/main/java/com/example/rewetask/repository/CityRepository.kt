package com.example.rewetask.repository

import com.example.rewetask.db.city.CityDao
import com.example.rewetask.model.LocalCityDetail
import com.example.rewetask.network.RetrofitClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CityRepository(
    private val cityDao: CityDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

    suspend fun searchCity(searchQuery: String): List<LocalCityDetail>? = withContext(ioDispatcher) {
        val cityResponse = runCatching {
            RetrofitClient.cityApiService.searchCity(
                query = searchQuery,
            ).execute().body()
        }.getOrNull()

        return@withContext cityResponse?.cities
    }

    suspend fun updateCity(cityDetail: LocalCityDetail) = withContext(ioDispatcher) {
        if (cityDao.findCityByName(cityDetail.name).firstOrNull() == null) {
            cityDao.insertOrReplace(cityDetail.toDb())
        } else {
            cityDao.deleteCityByName(cityName = cityDetail.name)
        }
    }

    suspend fun getCities(): Flow<List<LocalCityDetail>> = withContext(ioDispatcher) {
        return@withContext cityDao.getCities().map { cities ->
            cities.map {
                it.toDomain()
            }
        }
    }
}
