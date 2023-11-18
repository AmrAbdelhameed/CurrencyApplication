package com.example.currencyapplication.presentation.ui.convert_currency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyapplication.data.source.remote.Resource
import com.example.currencyapplication.domain.model.local.ConvertCurrency
import com.example.currencyapplication.domain.model.remote.CurrencyRatesResponse
import com.example.currencyapplication.domain.usecases.CurrencyRatesUseCase
import com.example.currencyapplication.domain.usecases.InsertConvertCurrencyUseCase
import com.example.currencyapplication.presentation.ui.convert_currency.intent.ConvertCurrencyIntent
import com.example.currencyapplication.presentation.ui.convert_currency.viewstate.ConvertCurrencyState
import com.example.currencyapplication.presentation.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ConvertCurrencyViewModel @Inject constructor(
    private val currencyRatesUseCase: CurrencyRatesUseCase,
    private val insertConvertCurrencyUseCase: InsertConvertCurrencyUseCase
) : ViewModel() {
    val convertCurrencyIntent = Channel<ConvertCurrencyIntent>(Channel.UNLIMITED)

    private val _state = MutableStateFlow<ConvertCurrencyState>(ConvertCurrencyState.Idle)
    val state: StateFlow<ConvertCurrencyState> = _state

    var from = CurrencyDataItem()
    var to = CurrencyDataItem()

    val currencyConvert = CurrencyConvert()

    val popularList = ArrayList<CurrencyDataItem>()

    private fun getPopularList(rates: Map<String, Double>) {
        popularList.clear()
        popularList.addAll(Utils.getPopularList(rates))
    }

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            convertCurrencyIntent.consumeAsFlow().collect {
                when (it) {
                    is ConvertCurrencyIntent.FetchRates -> getRatesResponseFlow()
                    is ConvertCurrencyIntent.InsertCurrencyConvert -> insertConvertCurrency()
                }
            }
        }
    }

    private fun getRatesResponseFlow() {
        viewModelScope.launch {
            _state.value = ConvertCurrencyState.Loading
            _state.value = when (val resource = currencyRatesUseCase.invoke()) {
                is Resource.Success -> {
                    val currencyRatesResponse: CurrencyRatesResponse? = resource.data

                    if (currencyRatesResponse?.success == true) {
                        getPopularList(currencyRatesResponse.rates)
                        ConvertCurrencyState.Success(currencyRatesResponse)
                    } else {
                        ConvertCurrencyState.Error(currencyRatesResponse?.error?.info)
                    }
                }

                is Resource.Error -> ConvertCurrencyState.Error(resource.error)
            }
            _state.value = ConvertCurrencyState.Idle
        }
    }

    private fun insertConvertCurrency() {
        if (currencyConvert.fromValue.isNotEmpty() && currencyConvert.toValue.isNotEmpty()) {
            viewModelScope.launch {
                insertConvertCurrencyUseCase.invoke(
                    ConvertCurrency(
                        fromName = from.name,
                        toName = to.name,
                        fromValue = currencyConvert.fromValue.toDouble(),
                        toValue = currencyConvert.toValue.toDouble(),
                        createdAt = Date()
                    )
                )
            }
        }
    }

    private fun getExchangeRate(amount: Double): Double {
        return (amount * to.rateValue) / from.rateValue
    }

    fun getConversionResult(): String {
        val result = getExchangeRate(currencyConvert.fromValue.toDouble())
        return String.format("%.4f", result)
    }
}