package com.example.currencyapplication.domain.model.remote

data class CurrencyRatesResponse(
    val base: String,
    val date: String,
    val rates: Map<String, Double>,
    val timestamp: Int,
    val success: Boolean,
    val error: Error
)