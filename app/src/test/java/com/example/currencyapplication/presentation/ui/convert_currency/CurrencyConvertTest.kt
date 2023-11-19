package com.example.currencyapplication.presentation.ui.convert_currency

import org.junit.Assert.assertEquals
import org.junit.Test

class CurrencyConvertTest {
    @Test
    fun `CurrencyConvert should have correct default values`() {
        val currencyConvert = CurrencyConvert()

        assertEquals(0, currencyConvert.fromPosition)
        assertEquals(0, currencyConvert.toPosition)
        assertEquals("1", currencyConvert.fromValue)
        assertEquals("", currencyConvert.toValue)
    }

    @Test
    fun `CurrencyConvert should have correct values when initialized`() {
        val oldFromPosition = 0
        val fromPosition = 1
        val oldToPosition = 0
        val toPosition = 2
        val fromValue = "5.0"
        val toValue = "7.5"

        val currencyConvert = CurrencyConvert(oldFromPosition, fromPosition, oldToPosition, toPosition, fromValue, toValue)

        assertEquals(oldFromPosition, currencyConvert.oldFromPosition)
        assertEquals(fromPosition, currencyConvert.fromPosition)
        assertEquals(oldToPosition, currencyConvert.oldToPosition)
        assertEquals(toPosition, currencyConvert.toPosition)
        assertEquals(fromValue, currencyConvert.fromValue)
        assertEquals(toValue, currencyConvert.toValue)
    }
}
