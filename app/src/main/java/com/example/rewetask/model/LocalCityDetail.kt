package com.example.rewetask.model

import com.example.rewetask.db.city.CityEntity
import kotlinx.serialization.Serializable

@Serializable
data class LocalCityDetail(
    val cityName: String = "",
    val countryName: String = "",
    val isBookmarked: Boolean? = null,
) {
    fun toDb(): CityEntity = CityEntity(
        cityName = this.cityName,
        countryName = this.countryName,
        isBookmarked = this.isBookmarked,
    )
}
