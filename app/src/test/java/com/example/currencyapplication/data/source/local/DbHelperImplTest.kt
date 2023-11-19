package com.example.currencyapplication.data.source.local

import com.example.currencyapplication.data.source.local.dao.CurrencyDao
import com.example.currencyapplication.data.source.local.helper.DbHelperImpl
import com.example.currencyapplication.domain.model.local.ConvertCurrency
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.Calendar
import java.util.Date

class DbHelperImplTest {
    private val rateDao: CurrencyDao = mockk(relaxed = true)
    private lateinit var dbHelper: DbHelperImpl

    @Before
    fun setUp() {
        dbHelper = DbHelperImpl(rateDao)
    }

    @Test
    fun `insertConvertCurrency should call rateDao`() = runBlocking {
        val convertCurrency = mockk<ConvertCurrency>()

        dbHelper.insertConvertCurrency(convertCurrency)

        coVerify { rateDao.insertConvertCurrency(convertCurrency) }
    }

    @Test
    fun `getAllCurrencyRates should return flow of expected result`() = runBlocking {
        val currentDate = Date(System.currentTimeMillis())
        val date = Calendar.getInstance()
        date.add(Calendar.DATE, -3)
        val dateFromThreeDaysAgo = date.time

        val expectedList = listOf(mockk<ConvertCurrency>())
        coEvery {
            rateDao.getAllCurrencyRates(
                currentDate,
                dateFromThreeDaysAgo
            )
        } returns expectedList

        val flowResult = dbHelper.getAllCurrencyRates()

        val actualList = flowResult.toList()
        assert(actualList.size == 1)
    }
}
