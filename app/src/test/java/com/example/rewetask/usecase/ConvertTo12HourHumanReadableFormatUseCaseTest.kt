package com.example.rewetask.usecase

import com.example.usecase.ConvertTo12HourHumanReadableFormatUseCase
import kotlinx.coroutines.test.TestResult
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ConvertTo12HourHumanReadableFormatUseCaseTest {

    private lateinit var convertTo12HourHumanReadableFormatUseCase: ConvertTo12HourHumanReadableFormatUseCase

    @BeforeEach
    fun setup() {
        convertTo12HourHumanReadableFormatUseCase = ConvertTo12HourHumanReadableFormatUseCase()
    }

    @ParameterizedTest
    @CsvSource(
        "0, Now",
        "300, 3AM",
        "1200, 12PM",
        "1300, 1PM",
        "1500, 3PM",
        "2300, 11PM"
    )
    fun `verify conversion to 12 hour human readable format`(input: String, expectedValue: String): TestResult = runTest {
        assertEquals(expectedValue, convertTo12HourHumanReadableFormatUseCase(input))
    }
}