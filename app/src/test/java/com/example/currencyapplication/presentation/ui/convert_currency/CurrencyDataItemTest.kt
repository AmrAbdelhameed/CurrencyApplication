package com.example.currencyapplication.presentation.ui.convert_currency

import org.junit.Assert.assertEquals
import org.junit.Test

class CurrencyDataItemTest {
    @Test
    fun `CurrencyDataItem should have correct default values`() {
        val currencyDataItem = CurrencyDataItem()

        assertEquals("", currencyDataItem.name)
        assertEquals(0.0, currencyDataItem.rateValue, 0.0)
    }

    @Test
    fun `CurrencyDataItem should have correct values when initialized`() {
        val name = "USD"
        val rateValue = 1.0

        val currencyDataItem = CurrencyDataItem(name, rateValue)

        assertEquals(name, currencyDataItem.name)
        assertEquals(rateValue, currencyDataItem.rateValue, 0.0)
    }
}
