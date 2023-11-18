package com.example.currencyapplication.presentation.ui.convert_currency

import android.os.Parcelable
import com.example.currencyapplication.presentation.base.ListAdapterItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrencyDataItem(
    val name: String = "",
    val rateValue: Double = 0.0,
    override var id: Long = 0
) : ListAdapterItem, Parcelable {
    override fun toString() = name
}