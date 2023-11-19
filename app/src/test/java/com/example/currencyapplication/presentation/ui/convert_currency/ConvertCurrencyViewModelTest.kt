package com.example.currencyapplication.presentation.ui.convert_currency

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.currencyapplication.data.source.remote.Resource
import com.example.currencyapplication.domain.model.remote.CurrencyRatesResponse
import com.example.currencyapplication.domain.usecases.CurrencyRatesUseCase
import com.example.currencyapplication.domain.usecases.InsertConvertCurrencyUseCase
import com.example.currencyapplication.presentation.ui.convert_currency.intent.ConvertCurrencyIntent
import com.example.currencyapplication.presentation.ui.convert_currency.viewstate.ConvertCurrencyState
import com.example.currencyapplication.presentation.utils.MainDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ConvertCurrencyViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val currencyRatesUseCase: CurrencyRatesUseCase = mockk(relaxed = true)
    private val insertConvertCurrencyUseCase: InsertConvertCurrencyUseCase = mockk(relaxed = true)

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    private lateinit var viewModel: ConvertCurrencyViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = ConvertCurrencyViewModel(currencyRatesUseCase, insertConvertCurrencyUseCase)
    }

    @After
    fun tearDown() {
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun `getRatesResponseFlow should update state correctly on success`() =
        testScope.runBlockingTest {
            val successResponse = Resource.Success(
                CurrencyRatesResponse(
                    "USD", "2023-11-18", emptyMap(), 0, true
                )
            )
            coEvery { currencyRatesUseCase.invoke() } returns successResponse

            viewModel.convertCurrencyIntent.send(ConvertCurrencyIntent.FetchRates)

            assertEquals(ConvertCurrencyState.Idle, viewModel.state.value)
        }

    @Test
    fun `getRatesResponseFlow should update state correctly on error`() =
        testScope.runBlockingTest {
            val errorResponse = Resource.Error<CurrencyRatesResponse>("Failed to fetch rates")
            coEvery { currencyRatesUseCase.invoke() } returns errorResponse

            viewModel.convertCurrencyIntent.send(ConvertCurrencyIntent.FetchRates)

            assertEquals(ConvertCurrencyState.Idle, viewModel.state.value)
        }

    @Test
    fun `insertConvertCurrency should invoke use case and update state`() =
        testScope.runBlockingTest {
            viewModel.currencyConvert.fromValue = "10.0"
            viewModel.currencyConvert.toValue = "15.0"

            viewModel.isInsertionEnabled = true

            viewModel.convertCurrencyIntent.send(ConvertCurrencyIntent.InsertCurrencyConvert)

            coVerify { insertConvertCurrencyUseCase.invoke(any()) }
        }

    @Test
    fun `getConversionResult should return correct result`() {
        viewModel.currencyConvert.fromValue = "10.0"
        viewModel.fromCurrencyDataItem = CurrencyDataItem(name = "USD", rateValue = 1.0)
        viewModel.toCurrencyDataItem = CurrencyDataItem(name = "EUR", rateValue = 0.85)

        val result = viewModel.getConversionResult()

        assertEquals("8.5000", result)
    }
}
