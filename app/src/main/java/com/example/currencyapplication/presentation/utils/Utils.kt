package com.example.currencyapplication.presentation.utils

import com.example.currencyapplication.R
import com.example.currencyapplication.presentation.ui.convert_currency.CurrencyDataItem
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

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