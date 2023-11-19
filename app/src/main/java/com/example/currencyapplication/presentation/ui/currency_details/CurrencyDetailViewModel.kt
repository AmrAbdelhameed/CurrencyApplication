package com.example.currencyapplication.presentation.ui.currency_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyapplication.domain.mapper.Mappers.mapToConvertCurrencyDataItem
import com.example.currencyapplication.domain.usecases.HistoricalUseCase
import com.example.currencyapplication.presentation.ui.convert_currency.CurrencyDataItem
import com.example.currencyapplication.presentation.ui.currency_details.history.ConvertCurrencyDataItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyDetailViewModel @Inject constructor(
    private val historicalUseCase: HistoricalUseCase
) : ViewModel() {
    private val _historyList = MutableLiveData<List<ConvertCurrencyDataItem>>()
    val historyList: LiveData<List<ConvertCurrencyDataItem>>
        get() = _historyList

    private val _chartList = MutableLiveData<List<Double>>()
    val chartList: LiveData<List<Double>>
        get() = _chartList

    private val _popularList = MutableLiveData<List<CurrencyDataItem>>()
    val popularList: LiveData<List<CurrencyDataItem>>
        get() = _popularList

    init {
        getHistoricalData()
    }

    fun getHistoricalData() {
        viewModelScope.launch {
            historicalUseCase.invoke().collect { currencies ->
                _chartList.value = currencies.map { it.toValue }
                _historyList.value = currencies.map { it.mapToConvertCurrencyDataItem() }
            }
        }
    }

    fun setPopularOtherCurrencies(currencyDataItemList: List<CurrencyDataItem>) {
        _popularList.value = currencyDataItemList
    }
}