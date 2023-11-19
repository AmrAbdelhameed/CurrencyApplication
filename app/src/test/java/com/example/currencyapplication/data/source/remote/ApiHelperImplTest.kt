package com.example.currencyapplication.data.source.remote

import com.example.currencyapplication.data.source.remote.helper.ApiHelperImpl
import com.example.currencyapplication.domain.model.remote.CurrencyRatesResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ApiHelperImplTest {
    private val apiService: ApiService = mockk()

    private lateinit var apiHelper: ApiHelperImpl

    @Before
    fun setUp() {
        apiHelper = ApiHelperImpl(apiService)
    }

    @Test
    fun `getCurrencyRatesResponse should return expected result`() = runBlocking {
        val expectedResponse = mockk<Response<CurrencyRatesResponse>>()
        coEvery { apiService.getCurrencyRatesResponse() } returns expectedResponse

        val actualResponse = apiHelper.getCurrencyRatesResponse()

        assertEquals(expectedResponse, actualResponse)
    }
}
