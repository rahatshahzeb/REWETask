package com.example.rewetask.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rewetask.model.Hourly
import com.example.rewetask.model.LocalWeatherDetail

@Composable
fun WeatherData(
    weatherDetail: LocalWeatherDetail,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .verticalScroll(scrollState)
    ) {
        Row {
            Card(
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                ) {
                    Text(
                        text = "Now",
                        fontSize = 16.sp
                    )
                    Spacer (Modifier.height(8.dp))
                    Text(
                        text = "${weatherDetail.tempC}º",
                        fontSize = 48.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }

            Spacer (modifier = Modifier
                .weight(1F)
                .fillMaxWidth())

            Card(
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column (
                    modifier = Modifier.padding(16.dp),
                )  {
                    Text(
                        text = weatherDetail.weatherDesc ?: "",
                        fontSize = 16.sp
                    )
                    Spacer (Modifier.height(8.dp))
                    Text(
                        text = "${weatherDetail.feelsLikeC}º",
                        modifier = Modifier
                            .align(Alignment.End),
                    )
                }
            }
        }

        Spacer (Modifier.height(16.dp))

        Text(
            text = "Hourly forecast",
            fontSize = 16.sp
        )

        Spacer (Modifier.height(4.dp))

        Card(
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            LazyRow {
                items(weatherDetail.hourlyData) { hourly->

                        Column(
                            modifier = Modifier.padding(16.dp),
                        ) {
                            Text(
                                text = "${hourly.tempC}º",
                                fontSize = 16.sp
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = hourly.time ?: "",
                                fontSize = 14.sp,
                                fontFamily = FontFamily.Monospace
                            )

                        }

                }
            }
        }

        Spacer (Modifier.height(16.dp))

        Row {
            Card(
                modifier = Modifier.weight(1F),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                ) {
                    Text(
                        text = "Wind",
                        fontSize = 16.sp
                    )
                    Spacer (Modifier.height(8.dp))
                    Text(
                        text = weatherDetail.windSpeedKmph ?: "X",
                        fontSize = 24.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }

            Spacer (Modifier.width(8.dp))

            Card(
                modifier = Modifier.weight(1F),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                ) {
                    Text(
                        text = "Humidity",
                        fontSize = 16.sp
                    )
                    Spacer (Modifier.height(8.dp))
                    Text(
                        text = weatherDetail.humidity ?: "X",
                        fontSize = 24.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }
        }

        Spacer (Modifier.height(8.dp))

        Row {
            Card(
                modifier = Modifier.weight(1F).padding(bottom = 16.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                ) {
                    Text(
                        text = "UV Index",
                        fontSize = 16.sp
                    )
                    Spacer (Modifier.height(8.dp))
                    Text(
                        text = weatherDetail.uvIndex ?: "",
                        fontSize = 24.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }

            Spacer (Modifier.width(8.dp))

            Card(
                modifier = Modifier.weight(1F),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                ) {
                    Text(
                        text = "Pressure",
                        fontSize = 16.sp
                    )
                    Spacer (Modifier.height(8.dp))
                    Text(
                        text = weatherDetail.pressure ?: "",
                        fontSize = 24.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun WeatherDataPreview() {
    val weatherDetail = LocalWeatherDetail(
        cityName = "Vienna",
        countryName = "Austria",
        tempC = "15",
        feelsLikeC = "Feels like 13",
        uvIndex = "0",
        pressure = "200",
        weatherDesc = "Clear with periodic clouds",
        windSpeedKmph = "3 km/h",
        humidity = "47%",
        hourlyData = listOf(
            Hourly(tempC = "10", time = "Now"),
            Hourly(tempC = "15", time = "1pm"),
            Hourly(tempC = "16", time = "2pm"),
            Hourly(tempC = "17", time = "3pm"),
            Hourly(tempC = "18", time = "4pm"),
            Hourly(tempC = "17", time = "5pm"),
        )
    )
    WeatherData(weatherDetail)
}