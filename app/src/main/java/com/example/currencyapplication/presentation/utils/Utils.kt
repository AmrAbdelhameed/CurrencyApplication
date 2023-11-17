package com.example.currencyapplication.presentation.utils

import com.example.currencyapplication.presentation.ui.convert_currency.CurrencyDataItem

object Utils {
    fun getPopularList(map: Map<String, Double>): MutableList<CurrencyDataItem> {
        val popularList = mutableListOf<CurrencyDataItem>()
        map.forEach {
            if (Constants.getPopularCurrencies().contains(it.key)) {
                popularList.add(CurrencyDataItem(name = it.key, rateValue = it.value))
            }
        }
        return popularList
    }
}