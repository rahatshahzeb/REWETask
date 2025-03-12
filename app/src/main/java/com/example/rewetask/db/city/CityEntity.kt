package com.example.rewetask.db.city

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.rewetask.model.LocalCityDetail

@Entity(primaryKeys = ["cityName", "countryName"])
data class CityEntity(
    @ColumnInfo val cityName: String,
    @ColumnInfo val countryName: String,
    @ColumnInfo(name = "is_Bookmarked") val isBookmarked: Boolean? = false,
) {
    fun toDomain(): LocalCityDetail = LocalCityDetail(
        cityName = this.cityName,
        countryName = this.countryName,
        isBookmarked = this.isBookmarked,
    )
}
