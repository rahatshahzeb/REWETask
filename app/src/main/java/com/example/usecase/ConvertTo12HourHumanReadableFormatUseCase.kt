package com.example.usecase

import android.annotation.SuppressLint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ConvertTo12HourHumanReadableFormatUseCase(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

    @SuppressLint("DefaultLocale")
    suspend operator fun invoke(time: String): String = withContext(ioDispatcher) {
        return@withContext if (time == "0") {
            "Now"
        } else {
            val hours = time.toLong() / 100
            val period = if (hours < 12) "AM" else "PM"
            val formattedHours = when {
                hours == 0L -> 12
                hours > 12 -> hours - 12
                else -> hours
            }
            String.format("%d%s", formattedHours, period)
        }
    }
}
