package com.example.currencyapplication.domain.usecases

import com.example.currencyapplication.domain.model.local.ConvertCurrency
import com.example.currencyapplication.domain.repository.ConvertCurrencyRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class InsertConvertCurrencyUseCaseTest {

    private val convertCurrencyRepository: ConvertCurrencyRepository = mockk(relaxed = true)
    private lateinit var insertConvertCurrencyUseCase: InsertConvertCurrencyUseCase

    @Before
    fun setUp() {
        insertConvertCurrencyUseCase = InsertConvertCurrencyUseCase(convertCurrencyRepository)
    }

    @Test
    fun `invoke should call insertConvertCurrency in repository`() = runBlocking {
        val convertCurrency = mockk<ConvertCurrency>()

        insertConvertCurrencyUseCase.invoke(convertCurrency)

        coEvery { convertCurrencyRepository.insertConvertCurrency(convertCurrency) }

        coVerify {
            convertCurrencyRepository.insertConvertCurrency(convertCurrency)
        }
    }
}
