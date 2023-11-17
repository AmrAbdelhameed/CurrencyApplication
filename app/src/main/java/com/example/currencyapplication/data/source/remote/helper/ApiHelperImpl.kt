package com.example.currencyapplication.data.source.remote.helper

import com.example.currencyapplication.data.source.remote.ApiService
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {
    override suspend fun getCurrencyRatesResponse() = apiService.getCurrencyRatesResponse()
}