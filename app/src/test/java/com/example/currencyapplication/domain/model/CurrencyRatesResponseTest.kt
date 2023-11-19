package com.example.currencyapplication.domain.model

import com.example.currencyapplication.domain.model.remote.CurrencyRatesResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class CurrencyRatesResponseTest {
    @Test
    fun `CurrencyRatesResponse should have correct values`() {
        val base = "USD"
        val date = "2023-11-18"
        val rates = mapOf("EUR" to 0.85, "GBP" to 0.75)
        val timestamp = 1637227200
        val success = true

        val currencyRatesResponse = CurrencyRatesResponse(base, date, rates, timestamp, success)

        assertEquals(base, currencyRatesResponse.base)
        assertEquals(date, currencyRatesResponse.date)
        assertEquals(rates, currencyRatesResponse.rates)
        assertEquals(timestamp, currencyRatesResponse.timestamp)
        assertEquals(success, currencyRatesResponse.success)
    }
}
