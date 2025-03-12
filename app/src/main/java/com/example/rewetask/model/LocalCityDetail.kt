package com.example.rewetask.model

import com.example.rewetask.db.city.CityEntity
import kotlinx.serialization.Serializable

@Serializable
data class LocalCityDetail(
    val name: String = "",
    val countryName: String = "",
    val isBookmarked: Boolean = false,
) {
    fun toDb(): CityEntity = CityEntity(
        cityName = this.name,
        countryName = this.countryName,
        isBookmarked = this.isBookmarked,
    )
}
