package com.example.rewetask.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rewetask.db.city.CityDao
import com.example.rewetask.db.city.CityEntity
import com.example.rewetask.db.weather.WeatherDao
import com.example.rewetask.db.weather.WeatherEntity

@Database(entities = [CityEntity::class, WeatherEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao
    abstract fun weatherDao(): WeatherDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "weather-db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
