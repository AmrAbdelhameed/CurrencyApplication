package com.example.currencyapplication.data.source.local.helper

import com.example.currencyapplication.data.source.local.dao.CurrencyDao
import com.example.currencyapplication.domain.model.local.ConvertCurrency
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class DbHelperImpl @Inject constructor(private val rateDao: CurrencyDao) : DbHelper {
    override suspend fun insertConvertCurrency(rateModel: ConvertCurrency) {
        rateDao.insertConvertCurrency(rateModel)
    }

    override suspend fun getAllCurrencyRates(): Flow<List<ConvertCurrency>> {
        val currentDate = Date(System.currentTimeMillis())
        val date = Calendar.getInstance()
        date.add(Calendar.DATE, -3)
        val dateFromThreeDats = date.time
        return flow { emit(rateDao.getAllCurrencyRates(currentDate, dateFromThreeDats)) }.flowOn(IO)
    }
}