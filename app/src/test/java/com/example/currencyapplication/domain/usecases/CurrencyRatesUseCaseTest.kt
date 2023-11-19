package com.example.currencyapplication.domain.usecases

import com.example.currencyapplication.data.source.remote.Resource
import com.example.currencyapplication.domain.model.remote.CurrencyRatesResponse
import com.example.currencyapplication.domain.repository.ConvertCurrencyRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CurrencyRatesUseCaseTest {

    private val convertCurrencyRepository: ConvertCurrencyRepository = mockk()
    private lateinit var currencyRatesUseCase: CurrencyRatesUseCase

    @Before
    fun setUp() {
        currencyRatesUseCase = CurrencyRatesUseCase(convertCurrencyRepository)
    }

    @Test
    fun `invoke should return expected result`() = runBlocking {
        val expectedResponse = mockk<Resource<CurrencyRatesResponse>>()
        coEvery { convertCurrencyRepository.getCurrencyRatesResponse() } returns expectedResponse

        val actualResponse = currencyRatesUseCase.invoke()

        assertEquals(expectedResponse, actualResponse)
    }
}
