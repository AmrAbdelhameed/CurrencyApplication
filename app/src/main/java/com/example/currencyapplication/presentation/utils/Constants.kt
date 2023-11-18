package com.example.currencyapplication.presentation.utils

object Constants {
    fun getPopularCurrencies() = listOf(
        "CAD", "CAD", "ALL", "JPY", "USD", "GBP", "AUD", "CHF", "CNY", "HKD", "NZD"
    )

    object BundleArguments {
        const val FROM_POSITION = "fromPosition"
        const val FROM_VALUE = "fromValue"
        const val TO_POSITION = "toPosition"
        const val TO_VALUE = "toValue"

        const val POPULAR_CURRENCIES = "popularCurrencies"
    }
}