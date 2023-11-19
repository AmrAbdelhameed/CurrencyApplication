package com.example.currencyapplication.presentation.ui.convert_currency

data class CurrencyConvert(
    var oldFromPosition: Int = 0,
    var fromPosition: Int = 0,
    var oldToPosition: Int = 0,
    var toPosition: Int = 0,
    var fromValue: String = "1",
    var toValue: String = ""
)