package com.example.rewetask.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.rewetask.db.AppDatabase
import com.example.rewetask.db.city.CityDao
import com.example.rewetask.model.LocalCityDetail
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
class CityRepositoryTest {

    private lateinit var cityDao: CityDao
    private lateinit var db: AppDatabase
    private lateinit var repository: CityRepository

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
        cityDao = db.cityDao()
        repository = CityRepository(cityDao, testDispatcher)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun verify_bookmarking_city() = runTest {
        // Given
        val cityDetail = LocalCityDetail(
            name = "Vienna",
            countryName = "Austria",
        )

        // When
        repository.updateCity(cityDetail)

        // Then
        assertEquals(
            repository.getCities().first()[0],
            cityDetail
        )
    }

    @Test
    fun verify_un_bookmarking_city() = runTest {
        // Given
        val viennaCityDetail = LocalCityDetail(
            name = "Vienna",
            countryName = "Austria",
        )
        val linzCityDetail = LocalCityDetail(
            name = "Linz",
            countryName = "Austria",
        )

        // When
        repository.updateCity(viennaCityDetail)
        repository.updateCity(linzCityDetail)
        repository.updateCity(viennaCityDetail)

        // Then
        assertEquals(
            1,
            repository.getCities().first().size
        )
        assertEquals(
            linzCityDetail,
            repository.getCities().first()[0]
        )
    }
}