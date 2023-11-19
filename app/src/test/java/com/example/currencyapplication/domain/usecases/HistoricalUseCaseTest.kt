package com.example.currencyapplication.domain.usecases

import com.example.currencyapplication.domain.model.local.ConvertCurrency
import com.example.currencyapplication.domain.repository.ConvertCurrencyRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HistoricalUseCaseTest {

    private val convertCurrencyRepository: ConvertCurrencyRepository = mockk()
    private lateinit var historicalUseCase: HistoricalUseCase

    @Before
    fun setUp() {
        historicalUseCase = HistoricalUseCase(convertCurrencyRepository)
    }

    @Test
    fun `invoke should return expected result`() = runBlocking {
        val expectedList = mockk<List<ConvertCurrency>>()
        coEvery { convertCurrencyRepository.getAllCurrencyRates() } returns flowOf(expectedList)

        val actualList = historicalUseCase.invoke().toList()

        assertEquals(expectedList, actualList.first())
    }
}
