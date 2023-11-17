package com.example.currencyapplication.data.source.remote.helper

import com.example.currencyapplication.domain.model.remote.CurrencyRatesResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getCurrencyRatesResponse(): Response<CurrencyRatesResponse>
}