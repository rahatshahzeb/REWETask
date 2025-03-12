package com.example.rewetask.db.city

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(vararg city: CityEntity)

    @Query("SELECT * FROM cityentity WHERE cityName = :cityName")
    fun findCityByName(cityName: String): Flow<CityEntity>

    @Query("SELECT * FROM CityEntity")
    fun getCities(): Flow<List<CityEntity>>

    @Query("DELETE FROM CityEntity WHERE cityName = :cityName")
    fun deleteCityByName(cityName: String)
}
