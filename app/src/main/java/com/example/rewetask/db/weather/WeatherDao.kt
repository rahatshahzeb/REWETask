package com.example.rewetask.db.weather

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(vararg weather: WeatherEntity)

    @Query("SELECT * FROM weatherentity WHERE cityName = :cityName")
    fun findWeatherByCityName(cityName: String): Flow<WeatherEntity>
}
