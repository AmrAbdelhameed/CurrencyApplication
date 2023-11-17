package com.example.currencyapplication.data.repository

import android.app.Application
import com.example.currencyapplication.data.source.local.helper.DbHelper
import com.example.currencyapplication.data.source.remote.helper.ApiHelper
import com.example.currencyapplication.domain.model.local.ConvertCurrency
import com.example.currencyapplication.domain.repository.ConvertCurrencyRepository
import javax.inject.Inject

class ConvertCurrencyRepositoryImpl @Inject constructor(
    application: Application,
    private val apiHelper: ApiHelper,
    private val dbHelper: DbHelper
) : BaseRepository(application), ConvertCurrencyRepository {
    override suspend fun getCurrencyRatesResponse() = safeApiCall { apiHelper.getCurrencyRatesResponse() }

    override suspend fun insertConvertCurrency(
        convertCurrency: ConvertCurrency
    ) = dbHelper.insertConvertCurrency(convertCurrency)

    override suspend fun getAllCurrencyRates() = dbHelper.getAllCurrencyRates()
}