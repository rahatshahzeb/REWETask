package com.example.rewetask.db.weather

import androidx.room.TypeConverter
import com.example.rewetask.model.Hourly
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HourlyListConverter(
    private val gson: Gson = Gson()
) {
    @TypeConverter
    fun fromHourlyList(hourlyList: List<Hourly>?): String {
        return gson.toJson(hourlyList)
    }

    @TypeConverter
    fun toHourlyList(data: String): List<Hourly> {
        val listType = object : TypeToken<List<Hourly>>() {}.type
        return gson.fromJson(data, listType) ?: emptyList()
    }
}