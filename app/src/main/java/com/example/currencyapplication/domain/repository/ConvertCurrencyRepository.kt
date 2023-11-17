package com.example.currencyapplication.domain.repository

import com.example.currencyapplication.data.source.remote.Resource
import com.example.currencyapplication.domain.model.local.ConvertCurrency
import com.example.currencyapplication.domain.model.remote.CurrencyRatesResponse
import kotlinx.coroutines.flow.Flow

interface ConvertCurrencyRepository {
    suspend fun getCurrencyRatesResponse(): Resource<CurrencyRatesResponse>
    suspend fun insertConvertCurrency(convertCurrency: ConvertCurrency)
    suspend fun getAllCurrencyRates(): Flow<List<ConvertCurrency>>
}