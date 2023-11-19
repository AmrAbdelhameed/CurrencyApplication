package com.example.currencyapplication.presentation.ui.currency_details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.currencyapplication.domain.mapper.Mappers.mapToConvertCurrencyDataItem
import com.example.currencyapplication.domain.model.local.ConvertCurrency
import com.example.currencyapplication.domain.usecases.HistoricalUseCase
import com.example.currencyapplication.presentation.ui.convert_currency.CurrencyDataItem
import com.example.currencyapplication.presentation.ui.currency_details.history.ConvertCurrencyDataItem
import com.example.currencyapplication.presentation.utils.MainDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Date

@ExperimentalCoroutinesApi
class CurrencyDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val historicalUseCase: HistoricalUseCase = mockk()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    private lateinit var viewModel: CurrencyDetailViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = CurrencyDetailViewModel(historicalUseCase)
    }

    @After
    fun tearDown() {
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun `getHistoricalData should update historyList and chartList correctly`() = testScope.runBlockingTest {
        val rates = listOf(
            ConvertCurrency("USD", 1.0, "EUR", 0.85, Date()),
            ConvertCurrency("EUR", 0.85, "USD", 1.0, Date())
        )
        val ratesFlow = flowOf(rates)
        coEvery { historicalUseCase.invoke() } returns ratesFlow

        val historyListObserver: Observer<List<ConvertCurrencyDataItem>> = mockk(relaxed = true)
        val chartListObserver: Observer<List<Double>> = mockk(relaxed = true)
        viewModel.historyList.observeForever(historyListObserver)
        viewModel.chartList.observeForever(chartListObserver)

        viewModel.getHistoricalData()

        verify { historyListObserver.onChanged(rates.map { it.mapToConvertCurrencyDataItem() }) }
        verify { chartListObserver.onChanged(rates.map { it.toValue }) }
    }

    @Test
    fun `setPopularOtherCurrencies should update popularList correctly`() {
        val popularCurrencies = listOf(
            CurrencyDataItem("USD", 1.0),
            CurrencyDataItem("EUR", 0.85),
            CurrencyDataItem("GBP", 0.75)
        )

        val popularListObserver: Observer<List<CurrencyDataItem>> = mockk(relaxed = true)
        viewModel.popularList.observeForever(popularListObserver)

        viewModel.setPopularOtherCurrencies(popularCurrencies)

        verify { popularListObserver.onChanged(popularCurrencies) }
    }
}
