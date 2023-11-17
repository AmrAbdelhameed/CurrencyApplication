package com.example.currencyapplication.presentation.ui.convert_currency

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrencyDataItem(
    val name: String = "",
    val rateValue: Double = 0.0
) : Parcelable {
    override fun toString() = name
}