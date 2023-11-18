package com.example.currencyapplication.presentation.ui.convert_currency.intent

sealed class ConvertCurrencyIntent {
    object FetchRates : ConvertCurrencyIntent()

    object InsertCurrencyConvert : ConvertCurrencyIntent()
}