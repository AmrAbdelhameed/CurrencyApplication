package com.example.currencyapplication.presentation.ui.convert_currency.viewstate

import com.example.currencyapplication.domain.model.remote.CurrencyRatesResponse

sealed class ConvertCurrencyState {
    object Idle : ConvertCurrencyState()
    object Loading : ConvertCurrencyState()
    data class Success(val data: CurrencyRatesResponse?) : ConvertCurrencyState()
    data class Error(val error: String?) : ConvertCurrencyState()
}