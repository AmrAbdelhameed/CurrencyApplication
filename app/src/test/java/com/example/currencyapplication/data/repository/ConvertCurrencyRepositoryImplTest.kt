package com.example.currencyapplication.data.repository

import android.app.Application
import com.example.currencyapplication.data.source.local.helper.DbHelper
import com.example.currencyapplication.data.source.remote.helper.ApiHelper
import com.example.currencyapplication.domain.model.local.ConvertCurrency
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ConvertCurrencyRepositoryImplTest {
    private val application: Application = mockk(relaxed = true)
    private val apiHelper: ApiHelper = mockk(relaxed = true)
    private val dbHelper: DbHelper = mockk(relaxed = true)

    private lateinit var repository: ConvertCurrencyRepositoryImpl

    @Before
    fun setUp() {
        repository = ConvertCurrencyRepositoryImpl(application, apiHelper, dbHelper)
    }

    @Test
    fun `insertConvertCurrency should call dbHelper`() = runBlocking {
        val convertCurrency = mockk<ConvertCurrency>()

        repository.insertConvertCurrency(convertCurrency)

        coEvery { dbHelper.insertConvertCurrency(convertCurrency) }
        coVerify {
            dbHelper.insertConvertCurrency(convertCurrency)
        }
    }

    @Test
    fun `getAllCurrencyRates should return expected result`() = runBlocking {
        val expectedList = mockk<Flow<List<ConvertCurrency>>>()
        coEvery { dbHelper.getAllCurrencyRates() } returns expectedList

        val actualList = repository.getAllCurrencyRates()

        assertEquals(expectedList, actualList)
    }
}
