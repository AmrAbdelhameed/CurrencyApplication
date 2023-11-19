package com.example.currencyapplication.presentation.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class UtilsTest {
    @Test
    fun `getPopularList should return the correct list of CurrencyDataItem`() {
        val inputMap = mapOf("USD" to 1.0, "EUR" to 0.85, "GBP" to 0.75, "JPY" to 110.0)

        val result = Utils.getPopularList(inputMap)

        assertEquals(3, result.size)
        assertEquals("USD", result[0].name)
        assertEquals(1.0, result[0].rateValue, 0.0)
        assertEquals("GBP", result[1].name)
        assertEquals(0.75, result[1].rateValue, 0.0)
        assertEquals("JPY", result[2].name)
        assertEquals(110.0, result[2].rateValue, 0.0)
    }

    @Test
    fun `getPopularList should handle an empty input map`() {
        val emptyMap = emptyMap<String, Double>()

        val result = Utils.getPopularList(emptyMap)

        assertEquals(0, result.size)
    }

    @Test
    fun `getPopularList should handle input map with no popular currencies`() {
        val inputMap = mapOf("AUD" to 1.4, "CAD" to 1.3, "NZD" to 1.7)

        val result = Utils.getPopularList(inputMap)

        assertEquals(3, result.size)
    }
}
