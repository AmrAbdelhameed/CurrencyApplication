package com.example.currencyapplication.data.source.local.helper

import com.example.currencyapplication.domain.model.local.ConvertCurrency
import kotlinx.coroutines.flow.Flow

interface DbHelper {
    suspend fun insertConvertCurrency(convertCurrency: ConvertCurrency)
    suspend fun getAllCurrencyRates(): Flow<List<ConvertCurrency>>
}