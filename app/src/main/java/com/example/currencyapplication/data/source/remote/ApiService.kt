package com.example.currencyapplication.data.source.remote

import com.example.currencyapplication.BuildConfig
import com.example.currencyapplication.data.utils.Constants
import com.example.currencyapplication.domain.model.remote.CurrencyRatesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Constants.NetworkEndpoints.CURRENCY_LATEST)
    suspend fun getCurrencyRatesResponse(
        @Query("access_key") accessKey: String = BuildConfig.ACCESS_KEY
    ): Response<CurrencyRatesResponse>
}